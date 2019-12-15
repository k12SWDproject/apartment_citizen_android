package com.example.projectswd.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.contract.LoginGoogleActivityContract;
import com.example.projectswd.model.LoginGoogle;
import com.example.projectswd.model.User;
import com.example.projectswd.presenters.LoginGoogleActivityPresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginGoogleActivity extends AppCompatActivity implements LoginGoogleActivityContract.view {
    static final int GOOGLE_SIGN = 123;
    FirebaseAuth mAuth;
    Button button_Login, button_Logout;
    TextView textView;
    ProgressBar progressBar;
    GoogleSignInClient mGoogleSignInClient;
    CircleImageView imageView;
    private String personName, token;
    private LoginGoogleActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_google);
        button_Login = findViewById(R.id.btn_login);
//        button_Logout = findViewById(R.id.btn_logout);
        textView = findViewById(R.id.textView);

        progressBar = findViewById(R.id.progress_circular);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestProfile()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginGoogleActivity.this.SignInGoogle();
            }
        });
//        button_Logout.setOnClickListener(v -> Logout());
//        if (mAuth.getCurrentUser() != null) {
//            FirebaseUser user = mAuth.getCurrentUser();
//            updateUI(user);
//        }

    }

    void SignInGoogle() {
        progressBar.setVisibility(View.VISIBLE);
        Intent intentSignIn = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intentSignIn, GOOGLE_SIGN);


    }
    private void initPresenter(){
        presenter = new LoginGoogleActivityPresenter(this);

    }

    private void getUser(String token, String username){
        initPresenter();
        presenter.getUser(token, username);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                if (account != null) {
                    firebaseAuthWithGoogle(account);
                }

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG", "firebaseAuthWithGoogle: " + account.getId());
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);
                    Log.d("TAG", "Sign Success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("TAG", user.getUid());
                    personName = user.getEmail();
                    initPresenter();
                    LoginGoogle loginGoogle = new LoginGoogle();
                    loginGoogle.setUid(user.getUid());
                    presenter.loginGoole(loginGoogle);



                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("TAG", "Sign Fail");
                    Toast.makeText(LoginGoogleActivity.this, "SignFailed", Toast.LENGTH_LONG).show();


                }

            }
        });

    }
//    private void updateUI(FirebaseUser user) {
//        textView.setText("");
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//
//
//            if (user != null) {
//
//
//                String email = user.getEmail();
//                String photo = String.valueOf(user.getPhotoUrl());
//                String phoneNumber = user.getPhoneNumber();
//                String uID = user.getUid();
//                System.out.println(user.getIdToken(true).toString());
//                textView.append("INFORMATION \n");
//                textView.append("--------------------- \n");
//                textView.append("Name:" + personName + "\n");
//                textView.append("Email1: " + email + "\n");
//                textView.append("Email2: " + personEmail + "\n");
//                textView.append("Phone Number: " + phoneNumber + "\n");
//                textView.append("User ID1: " + uID + "\n");
//                textView.append("User ID2: " + personId + "\n");
//                imageView = findViewById(R.id.imageView);
//
//                Picasso.get().load(photo)
//                        .into(imageView);
//
//
//
//                button_Login.setVisibility(View.INVISIBLE);
//                button_Logout.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.INVISIBLE);
//
//
//            } else {
//                textView.setText("Failed");
//                button_Login.setVisibility(View.VISIBLE);
//                button_Logout.setVisibility(View.INVISIBLE);
//                progressBar.setVisibility(View.INVISIBLE);
//
//
//            }
//        }
//    }

    @Override
    public void loginGooleSuccess(String token) {
        if(token == null){
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }else{
        this.token = token;
        initPresenter();
        presenter.getUser(token,personName);}
    }

    @Override
    public void loginGooleFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserSuccess(User user) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("USERINFO", user);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }

    @Override
    public void getUserFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
