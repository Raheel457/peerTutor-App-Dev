package com.example.peertutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView toRegistration;
    EditText email,pass;
    Button Login;
    ProgressBar loading;
    CheckBox checkBox;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        mAuth = FirebaseAuth.getInstance();
        loading = (ProgressBar) findViewById(R.id.progressBar2);
        Login = (Button) findViewById(R.id.login);
        toRegistration = (TextView) findViewById(R.id.no_acc);

        toRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = (EditText) findViewById(R.id.email);
                pass = (EditText) findViewById(R.id.pass);
                checkBox = (CheckBox) findViewById(R.id.remMe1);

                String Email = String.valueOf(email.getText());
                String Pass = String.valueOf(pass.getText());
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(login.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(Pass)){
                    Toast.makeText(login.this, "Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                    }
                loading.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(Email, Pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loading.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    if (checkBox.isChecked()){
                                    SharedPreferences shrd = getSharedPreferences("user",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shrd.edit();

                                    editor.putString("email",Email);
                                    editor.putString("password",Pass);
                                    editor.apply();
                                    }

                                    Intent intent = new Intent(login.this, home.class);
                                    startActivity(intent);


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });

    }
}