package com.example.quiz;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quiz.models.Answer;
import com.example.quiz.models.Question;
import com.example.quiz.network.GetDataService;
import com.example.quiz.network.RetrofitClientInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentQuestion extends Fragment {
    View view;

    private static final String TAG = "FragmentQuestion";

    private TextView tvQuestion;
    private TextView tvQuestionNumber;
    private Button btnClickedAnswer;
    private Button btnConfirm;
    private LinearLayoutCompat clLayout;

    private ArrayList<Button> buttons = new ArrayList<>();

    private Question currentQuestion;
    private Answer clickedAnswer;

    private int questionListSize;
    private int questionIndex;
    private int idClickedAnswer;
    private int score;
    private int counter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_question, container, false);

        score = ((MainActivity)getActivity()).getScore();
        counter = ((MainActivity)getActivity()).getCounter();
        Log.d(TAG, "onClick: score = " + score);


        clickedAnswer = new Answer(Integer.MAX_VALUE, "", 0);

        questionIndex = ((MainActivity)getActivity()).getQuestionIndex();
        currentQuestion = ((MainActivity)getActivity()).getQuestion(questionIndex);
        questionListSize = ((MainActivity)getActivity()).getQuestionListSize();

        tvQuestion = view.findViewById(R.id.tvQuestion);
        tvQuestionNumber = view.findViewById(R.id.tvQuestionNumber);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        clLayout = view.findViewById(R.id.answersContainer);

        tvQuestion.setText(currentQuestion.getQuestion());
        tvQuestionNumber.setText("Question " + counter + " of " + questionListSize);
        currentQuestion.getAnswers().forEach((answer -> {
            View lAnswer = getLayoutInflater().inflate(R.layout.layout_answer_button, container, false);
            Button btnAnswer = lAnswer.findViewById(R.id.btnAns);
            btnAnswer.setText(answer.getAnswer());
            btnAnswer.setId(answer.getAnswerId());
            btnAnswer.setTag(answer.getIsCorrect());
            buttons.add(btnAnswer);
            btnAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickedAnswer.getAnswerId() != Integer.MAX_VALUE) {
                        return;
                    }
                    clickedAnswer = answer;
                    if (clickedAnswer.getIsCorrect() == 1) {
                        ((MainActivity)getActivity()).incScore();
                    }

                    idClickedAnswer = btnAnswer.getId();
                    buttons.forEach(button -> {
                        if (Integer.parseInt(button.getTag().toString()) == 1){
                            button.setBackground(getResources().getDrawable(R.drawable.btn_right_answer));
                            button.setTextColor(getResources().getColor(R.color.greenRightAnswer));
                        } else if (button.getId() == idClickedAnswer && Integer.parseInt(button.getTag().toString()) == 0) {
                            button.setBackground(getResources().getDrawable(R.drawable.btn_wrong_answer));
                            button.setTextColor(getResources().getColor(R.color.redWrongAnswer));
                        }
                        else {
                            button.setBackground(getResources().getDrawable(R.drawable.btn_not_filled));
                            button.setTextColor(getResources().getColor(R.color.mainThemeColor));
                        }
                    });
                }
            });
            clLayout.addView(lAnswer);
        }));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm= ((MainActivity)getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fm.beginTransaction();
                if (questionIndex >= 19) {
                    fragmentTransaction.replace(R.id.flMainView, new FragmentResults());
                    fragmentTransaction.commit();
                } else {
                    ((MainActivity)getActivity()).incCounter();
                    fragmentTransaction.replace(R.id.flMainView, new FragmentQuestion());
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }


}
