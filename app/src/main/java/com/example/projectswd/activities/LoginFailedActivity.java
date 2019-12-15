package com.example.projectswd.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFailedActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_failed);
        textView = findViewById(R.id.textViewLog);
        textView.setText("Bạn chưa có nhà. Vui lòng liên hệ chủ nhà để được thêm vào nhà");
    }


    public void clickToLogoutGoogle(View view) {
        logout();
    }
    void logout() {
        FirebaseAuth.getInstance().signOut();
        if (LoginGoogleActivity.mGoogleSignInClient != null) {
            LoginGoogleActivity.mGoogleSignInClient.signOut().addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
        } else {
            Toast.makeText(this, "CC", Toast.LENGTH_SHORT).show();
        }


    }
}
