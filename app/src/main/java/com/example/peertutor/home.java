package com.example.peertutor;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.peertutor.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Fragment_Home());
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homeBtn:
                    replaceFragment(new Fragment_Home());
                    break;
                case R.id.questionBtn:
                    replaceFragment(new Fragment_My_Questions());
                    break;
                case R.id.settingBtn:
                    replaceFragment(new Fragment_Settings());
                    break;

            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}