package com.example.peertutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class make_question extends AppCompatActivity {
    Button cancel,postQuestion;
    EditText title,description;
    FirebaseUser firebaseUser;
    FirebaseDatabase db;
    DatabaseReference questionRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_question);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        cancel = (Button) findViewById(R.id.cancel);
        postQuestion = (Button) findViewById(R.id.post);
        db = FirebaseDatabase.getInstance();
        questionRef = db.getReference("questions");



        postQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = (EditText) findViewById(R.id.nTitle);
                description = (EditText) findViewById(R.id.nDescription);


                String newTitle = String.valueOf(title.getText());
                String newDescription = String.valueOf(description.getText());

                if(newTitle.isEmpty()){
                    Toast.makeText(make_question.this, "Enter the Title",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (newDescription.isEmpty()){
                    Toast.makeText(make_question.this, "Enter Description for title",Toast.LENGTH_SHORT).show();
                    return;
                }
                QuestionModel question = new QuestionModel(newTitle,newDescription);
                questionRef.child(cleanMail(firebaseUser.getEmail())).setValue(question).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(make_question.this, "Posted Successfully",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }
                });

            }

            private String cleanMail(String nEmail) {
                String toReturn="";
                for(int i=0 ; nEmail.charAt(i)!='@';i++){
                    if (nEmail.charAt(i)!='.'){
                        toReturn+=nEmail.charAt(i);
                    }
                }
                return toReturn;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(this, home.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}