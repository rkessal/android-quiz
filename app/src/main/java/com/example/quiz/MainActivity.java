package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quiz.models.Question;
import com.example.quiz.network.GetDataService;
import com.example.quiz.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    ArrayList<Question> questions = new ArrayList<Question>();

    private int questionIndex = 0;
    private int score = 0;
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.flMainView,new FragmentWelcome());
        fragmentTransaction.commit();

        GetDataService service =  RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        service.getQuestions().enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                if (response.body() != null) {
                    questions.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public Question getQuestion(int index) {
        if (questionIndex < getQuestionListSize() - 1) {
            questionIndex++;
        }
        return questions.get(index);
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getQuestionListSize() { return questions.size(); }

    public int getScore() {
        return this.score;
    }

    public void incScore() {
        this.score++;
    }

    public int getCounter() { return this.counter; }

    public void incCounter() { this.counter++; }
}