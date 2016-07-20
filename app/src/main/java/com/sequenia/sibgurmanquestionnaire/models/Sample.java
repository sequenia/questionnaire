package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class Sample extends RealmObject implements Serializable{
    @PrimaryKey
    int id;
    int idQuestionary;
    String name;
    RealmList<Answerd> answerds;

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

    public RealmList<Answerd> getAnswerd() {
        return answerds;
    }

    public void setAnswerd(RealmList<Answerd> answerds) {
        this.answerds = answerds;
    }

    public int getIdQuestionary() {
        return idQuestionary;
    }

    public void setIdQuestionary(int idQuestionary) {
        this.idQuestionary = idQuestionary;
    }

    public RealmList<Answerd> getAnswerds() {
        return answerds;
    }

    public void setAnswerds(RealmList<Answerd> answerds) {
        this.answerds = answerds;
    }
}