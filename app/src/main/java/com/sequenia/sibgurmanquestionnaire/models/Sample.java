package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmObject;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class Sample implements Serializable{
    int id;
    String name;
    ArrayList<Answerd> answerds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Answerd> getAnswerd() {
        return answerds;
    }

    public void setAnswerd(ArrayList<Answerd> answerds) {
        this.answerds = answerds;
    }
}