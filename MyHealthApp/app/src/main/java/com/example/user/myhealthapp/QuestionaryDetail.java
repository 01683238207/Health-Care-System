package com.example.user.myhealthapp;

/**
 * Created by USER on 5/8/2017.
 */

public class QuestionaryDetail {
    String question;
    float weight,result;
    int responce=-1;

    public QuestionaryDetail(String question, float weight) {
        this.question = question;
        this.weight = weight;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public int getResponce() {
        return responce;
    }

    public void setResponce(int responce) {
        this.responce = responce;
    }
}
