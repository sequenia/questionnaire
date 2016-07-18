package com.sequenia.sibgurmanquestionnaire.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class Questionnaire extends RealmObject {

    @PrimaryKey
    private int id;
    private int schemaQuestions;
    private String schemaAnswer;
    private boolean answered;
    private boolean actual;
    private boolean departure;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchemaQuestions() {
        return schemaQuestions;
    }

    public void setSchemaQuestions(int schemaQuestions) {
        this.schemaQuestions = schemaQuestions;
    }

    public String getSchemaAnswer() {
        return schemaAnswer;
    }

    public void setSchemaAnswer(String schemaAnswer) {
        this.schemaAnswer = schemaAnswer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public boolean isDeparture() {
        return departure;
    }

    public void setDeparture(boolean departure) {
        this.departure = departure;
    }
}
