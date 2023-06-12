package com.example.peertutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button toLogin,toRegister;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences shrd = getSharedPreferences("user",MODE_PRIVATE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar4);

        progressBar.setVisibility(View.VISIBLE);


        String email = shrd.getString("email","");
        String pass = shrd.getString("password","");
        if (email!="" && pass != ""){
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                }
            });
        }
        else{
            progressBar.setVisibility(View.GONE);
        }

        toLogin = (Button) findViewById(R.id.toLogin);
        toRegister = (Button) findViewById(R.id.toRegister);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }
        });
    }
}