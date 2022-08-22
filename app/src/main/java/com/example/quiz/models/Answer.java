package com.example.quiz.models;

public class Answer {

    private int AnswerID;
    private String Answer;

    private int isCorrect;

    public Answer(int answerId, String answer, int isCorrect) {
        AnswerID = answerId;
        Answer = answer;
        this.isCorrect = isCorrect;
    }

    public void setAnswerID(int answerID) {
        AnswerID = answerID;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getAnswerId() {
        return AnswerID;
    }

    public String getAnswer() {
        return Answer;
    }

    public int getIsCorrect() {
        return isCorrect;
    }
}
