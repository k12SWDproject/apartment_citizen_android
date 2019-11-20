package com.example.projectswd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectswd.apiservice.APIService;
import com.example.projectswd.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickToLogin(View view) {
        edtUsername = findViewById(R.id.txtUsername);
        final String username = edtUsername.getText().toString();
        edtPassword = findViewById(R.id.txtPassword);
        String password = edtPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build();

        APIService json = retrofit.create(APIService.class);
        Call<User> call = json.getInforByUsername(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User user = new User();
                    user = response.body();
                    Toast.makeText(LoginActivity.this, user.getId()+"----", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "Failed ----", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "dasdsasa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
