package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeRaing implements Serializable {

    int id;
    int id_question;
    String commit;
    String minTranscript;
    String maxTranscript;
    int min;
    int max;
    int degres;

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

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getMinTranscript() {
        return minTranscript;
    }

    public void setMinTranscript(String minTranscript) {
        this.minTranscript = minTranscript;
    }

    public String getMaxTranscript() {
        return maxTranscript;
    }

    public void setMaxTranscript(String maxTranscript) {
        this.maxTranscript = maxTranscript;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getDegres() {
        return degres;
    }

    public void setDegres(int degres) {
        this.degres = degres;
    }
}
