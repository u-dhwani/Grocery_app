package com.example.dhwanigrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dhwanigrocerystore.MainActivity;
import com.example.dhwanigrocerystore.R;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {
    private Button login;
    private Button signup;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login=findViewById(R.id.welcome_login);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        signup=findViewById(R.id.welcome_register);
        if(auth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(Welcome.this, MainActivity.class));
            Toast.makeText(this, "Please Wait...You are already logged in!", Toast.LENGTH_SHORT).show();
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, LoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this, RegisterActivity.class));
            }
        });
    }
}