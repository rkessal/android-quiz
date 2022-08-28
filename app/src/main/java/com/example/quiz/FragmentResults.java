package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentResults extends Fragment {

    View view;
    private static final String TAG = "FragmentResults";

    private int score;

    private TextView tvResult;
    private Button btnPlayAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_results, container, false);

        score = ((MainActivity)getActivity()).getScore();
        btnPlayAgain = view.findViewById(R.id.btnRestart);
        tvResult = view.findViewById(R.id.tvResults);

        Log.d(TAG, "onCreateView: " + score);

        tvResult.setText(String.valueOf(score + " right answers"));

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainActivity)getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}