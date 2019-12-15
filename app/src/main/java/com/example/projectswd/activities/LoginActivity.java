package com.example.projectswd.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.contract.LoginActivityContract;
import com.example.projectswd.fragments.HomeFragment;
import com.example.projectswd.presenters.LoginActivityPresenter;
import com.example.projectswd.presenters.LoginGoogleActivityPresenter;
import com.example.projectswd.repositories.APIService;
import com.example.projectswd.model.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.view {

    EditText edtUsername, edtPassword;
    EditText txtTemp;
    private LoginActivityPresenter presenter;
    private String token;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void initPresenter(){
        presenter = new LoginActivityPresenter(this);
    }

    public void clickToLogin(View view) {
        edtUsername = findViewById(R.id.txtUsername);
         username = edtUsername.getText().toString();
        edtPassword = findViewById(R.id.txtPassword);
         password = edtPassword.getText().toString();


       initPresenter();
       presenter.login(username, password);

    }
//    private void  getInfo(final String token){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://spwproject.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
//
//        APIService json = retrofit.create(APIService.class);
//        Call<User> call = json.getInforByUsername(token, username);
//        call.enqueue(new Callback<User>() {
//         @Override
//        public void onResponse(Call<User> call, Response<User> response) {
//        if(response.isSuccessful()){
//                        User user = new User();
//                        user = response.body();
//
//                     Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                     intent.putExtra("USERINFO", user);
//                     intent.putExtra("TOKEN", token);
//                     startActivity(intent);
//                     return;
//
//                }
//                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//
//    @Override
//    public void onFailure(Call<User> call, Throwable t) {
//        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//    }
//});
//
//
//    }

    public void clickToLoginGoogle(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginGoogleActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccess(String token) {
        this.token = token;
        initPresenter();
        presenter.getUser(token, username);

    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserSuccess(User user) {
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        intent.putExtra("TOKEN", token);
        intent.putExtra("USERNAME", username);
        intent.putExtra("USERINFO", user);
        startActivity(intent);
    }

    @Override
    public void getUserFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
