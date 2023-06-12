package com.example.peertutor;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class registration extends AppCompatActivity {
    int year, month, date;
    DatePickerDialog datePickerDialog;
    TextView toLogin;
    EditText name, email, pass, sem;
    CheckBox checkBox;
    Button dateBtn,regBtn;
    FirebaseAuth mAuth;
    ProgressBar loader;
    FirebaseDatabase db;
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);
        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        regBtn = (Button) findViewById(R.id.register);
        toLogin = (TextView) findViewById(R.id.Acc_exist);
        dateBtn = (Button) findViewById(R.id.datePicker);
        loader = (ProgressBar) findViewById(R.id.progressBar2);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
            }
        });
        Calendar calender = Calendar.getInstance();

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                date = calender.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateBtn.setText(dayOfMonth + "-" + getMonth(month) + "-" + year);
                    }

                    private String getMonth(int month) {
                        month++;
                        switch (month){
                            case 1:
                                return "Jan";
                            case 2:
                                return "Fab";
                            case 3:
                                return "Mar";
                            case 4:
                                return "Apr";
                            case 5:
                                return "May";
                            case 6:
                                return "Jun";
                            case 7:
                                return "Jul";
                            case 8:
                                return "Aug";
                            case 9:
                                return "Sep";
                            case 19:
                                return "Oct";
                            case 11:
                                return "Nov";
                            case 12:
                                return "Dec";
                            default:
                                return "Jan";
                        }
                    }
                },year,month,date);
                datePickerDialog.show();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = (EditText) findViewById(R.id.newName);
                email = (EditText) findViewById(R.id.newEmail);
                pass = (EditText) findViewById(R.id.newPass);
                sem = (EditText) findViewById(R.id.semester);
                checkBox = (CheckBox) findViewById(R.id.remMe2);
                dateBtn = (Button) findViewById(R.id.datePicker);

                String nName = String.valueOf(name.getText());
                String nEmail = String.valueOf(email.getText());
                String nPass = String.valueOf(pass.getText());

                String semester = String.valueOf(sem.getText());
                String date = String.valueOf(dateBtn.getText());
                String checked = String.valueOf(checkBox.getText());

                if (TextUtils.isEmpty(nName)){
                    Toast.makeText(registration.this, "Enter a Name",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(nEmail)){
                    Toast.makeText(registration.this, "Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(nPass)){
                    Toast.makeText(registration.this, "Fill Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(String.valueOf(semester))){
                    Toast.makeText(registration.this, "Enter you semester",Toast.LENGTH_SHORT).show();
                    return;
                }

                loader.setVisibility(View.VISIBLE);
                userReference = db.getReference("user");
                User user = new User(nName,date,nEmail,Integer.parseInt(semester));

                userReference.child(cleanMail(nEmail)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                             name.setText("");
                             email.setText("");
                             sem.setText("");
                             pass.setText("");
                    }
                });
                mAuth.createUserWithEmailAndPassword(nEmail, nPass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loader.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(registration.this, home.class);
                                    startActivity(intent);
                                    Toast.makeText(registration.this, " Registration Completed.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(registration.this, "Registeration failed.",
                                            Toast.LENGTH_SHORT).show();
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
    }
}