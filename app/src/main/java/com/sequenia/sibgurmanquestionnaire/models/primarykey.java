package com.sequenia.sibgurmanquestionnaire.models;

/**
 * Created by ivan1 on 20.07.2016.
 */
public class primarykey {
    int id;
    int sample_id;
    int interview_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSample_id() {
        return sample_id;
    }

    public void setSample_id(int sample_id) {
        this.sample_id = sample_id;
    }

    public int getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(int interview_id) {
        this.interview_id = interview_id;
    }
}
