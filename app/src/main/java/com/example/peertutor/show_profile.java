package com.example.peertutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class show_profile extends AppCompatActivity {
    DatabaseReference userReference;
    FirebaseUser firebaseUser;
    TextView name,email,semester,dob;
    Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        name = (TextView) findViewById(R.id.pName);
        email = (TextView) findViewById(R.id.pEmail);
        semester = (TextView) findViewById(R.id.pSemester);
        dob = (TextView) findViewById(R.id.pDob);
        goBack = (Button) findViewById(R.id.backToHome);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userReference = FirebaseDatabase.getInstance().getReference();
        userReference.child("user").child(cleanEmail(firebaseUser.getEmail()));

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private String cleanEmail(String nEmail) {
        String toReturn="";
        for(int i=0 ; nEmail.charAt(i)!='@';i++){
            if (nEmail.charAt(i)!='.'){
                toReturn+=nEmail.charAt(i);
            }
        }
        return toReturn;
    }
}