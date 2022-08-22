package com.example.quiz.models;

import java.util.ArrayList;

public class Question {

    private int QuestionID;
    private String Question;
    private ArrayList<Answer> Answers;

    public Question(int questionID, String question, ArrayList<Answer> answers) {
        QuestionID = questionID;
        Question = question;
        Answers = answers;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public String getQuestion() {
        return Question;
    }

    public ArrayList<Answer> getAnswers() {
        return Answers;
    }
}
