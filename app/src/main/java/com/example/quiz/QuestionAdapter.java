package com.example.quiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quiz.models.Question;

import java.util.ArrayList;

public class QuestionAdapter extends ArrayAdapter<Question> {

    public QuestionAdapter(Context context, ArrayList<Question> questions) {
        super(context, 0, questions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        return super.getView(position, convertView, parent);
    }
}
