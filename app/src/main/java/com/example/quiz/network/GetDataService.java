package com.example.quiz.network;

import com.example.quiz.models.Question;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("Questions.php")
    Call<ArrayList<Question>> getQuestions();
}
