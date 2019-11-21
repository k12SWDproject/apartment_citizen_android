package com.example.projectswd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.apiservice.APIService;
import com.example.projectswd.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    EditText txtTemp;
    Button btnLogin;
    String token;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickToLogin(View view) {
        edtUsername = findViewById(R.id.txtUsername);
         username = edtUsername.getText().toString();
        edtPassword = findViewById(R.id.txtPassword);
        String password = edtPassword.getText().toString();
        txtTemp = findViewById(R.id.txtTmp);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://spwproject.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

        APIService json = retrofit.create(APIService.class);
        Call<ResponseBody> call = json.gettToken(username,password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){

                    try {
                        token = (response.body().string());
                        getInfo(token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;

                }
                Toast.makeText(LoginActivity.this, "Failed ----", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                txtTemp.setText("---------------------");
            }
        });

    }
    private void  getInfo(final String token){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://spwproject.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

        APIService json = retrofit.create(APIService.class);
        Call<User> call = json.getInforByUsername(username, token);
        call.enqueue(new Callback<User>() {
         @Override
        public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()){
                        User user = new User();
                        user = response.body();
                     Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                     intent.putExtra("USERINFO", user);
                        intent.putExtra("TOKEN", token);
                     startActivity(intent);
                        return;

                }
                Toast.makeText(LoginActivity.this, "Failed ----", Toast.LENGTH_SHORT).show();
                return;
            }


    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "LLLLLLLLLLLLLL", Toast.LENGTH_SHORT).show();
    }
});
//        call.enqueue(new Callback<ResponseBody>() {

    }
}
