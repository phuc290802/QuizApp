package com.example.appquiz.model;

public class TestModel {
    private  int testId;
    private int topScore;
    private int time;

    public TestModel(int testId, int topScore, int time) {
        this.testId = testId;
        this.topScore = topScore;
        this.time = time;
    }

    public TestModel() {
    }

    public int getTestId() {
        return testId;
    }

    public int getTopScore() {
        return topScore;
    }

    public int getTime() {
        return time;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
