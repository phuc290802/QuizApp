package com.example.appquiz.model;

public class CategoryModel {
    private int docID;
    private String name;
    private int noOfTests;

    public CategoryModel(int docID, String name, int noOfTests) {
        this.docID = docID;
        this.name = name;
        this.noOfTests = noOfTests;
    }

    public int getDocID() {
        return docID;
    }

    public String getName() {
        return name;
    }

    public int getNoOfTests() {
        return noOfTests;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoOfTests(int noOfTests) {
        this.noOfTests = noOfTests;
    }
}
