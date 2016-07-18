package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class Question implements Serializable {

    int id;
    String  name;
    int type;
    boolean required;
    boolean answered;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
