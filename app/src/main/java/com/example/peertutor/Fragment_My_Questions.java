package com.example.peertutor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_My_Questions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_My_Questions extends Fragment {

//    RecyclerView recyclerView;
//    ArrayList<QuestionModel> arrayList = new ArrayList<>();
    View parentholder;
    Button askQuestion;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_My_Questions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_My_Questions.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_My_Questions newInstance(String param1, String param2) {
        Fragment_My_Questions fragment = new Fragment_My_Questions();
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
        parentholder =inflater.inflate(R.layout.fragment__my__questions,null);
        askQuestion = (Button) parentholder.findViewById(R.id.makeQuestion);

        askQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), make_question.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return parentholder;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



//        dataInitialized();
//        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycleView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        RcAdapter adapter = new RcAdapter(getContext(),arrayList);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


    }

//    private void dataInitialized() {
//        arrayList.add(new QuestionModel("What is OOP","It is oop"));
//        arrayList.add(new QuestionModel("What is Noob","It is you"));
//        arrayList.add(new QuestionModel("What is BOOB","You old enough"));
//        arrayList.add(new QuestionModel("What is Soup","It is groove"));
//    }
}