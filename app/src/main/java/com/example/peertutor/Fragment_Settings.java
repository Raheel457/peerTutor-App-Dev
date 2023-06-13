package com.example.peertutor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button logout;
    LinearLayout layout,changePass;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View parentholder;
    TextView userName;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    ProgressBar progressBar;
    FirebaseDatabase db;
    DatabaseReference userReference;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Settings newInstance(String param1, String param2) {
        Fragment_Settings fragment = new Fragment_Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        parentholder =inflater.inflate(R.layout.fragment__settings,null);

        layout = (LinearLayout)  parentholder.findViewById(R.id.toMyProfile);
        changePass = (LinearLayout)  parentholder.findViewById(R.id.toChangePass);

        db = FirebaseDatabase.getInstance();
        userReference = db.getReference("user");
        userName = (TextView) parentholder.findViewById(R.id.setting_user);
        logout = (Button) parentholder.findViewById(R.id.btnLogout);
        progressBar = (ProgressBar) parentholder.findViewById(R.id.progressBar3);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences shrd = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        userReference.child(cleanEmail(firebaseUser.getEmail())).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot dataSnapshot = task.getResult();
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        userName.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                    }
                }
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), show_profile.class);
                startActivity(intent);
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePass.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                editor.remove("email");
                editor.remove("password");
                editor.apply();
                signOutUser();

            }

            private void signOutUser() {
                Toast.makeText(getActivity(), "Logged Out.",
                      Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return parentholder;
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