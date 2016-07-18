package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeFree implements Serializable {

    int id;
    int id_question;
    String positive;
    String negative;

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

}
