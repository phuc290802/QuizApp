package com.example.appquiz.model;

public class RankModel {
    private int id;
    private String name;
    private int score;
    private int second;
    private int minute;
    private int correct;
    private int wrong;
    private int unattemp;
    private int testId;

    public RankModel(int id, String name, int score, int second, int minute, int correct, int wrong, int unattemp, int testId) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.second = second;
        this.minute = minute;
        this.correct = correct;
        this.wrong = wrong;
        this.unattemp = unattemp;
        this.testId = testId;
    }

    public RankModel() {
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getUnattemp() {
        return unattemp;
    }

    public void setUnattemp(int unattemp) {
        this.unattemp = unattemp;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
