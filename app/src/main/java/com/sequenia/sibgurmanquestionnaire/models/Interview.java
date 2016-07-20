package com.sequenia.sibgurmanquestionnaire.models;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan1 on 19.07.2016.
 */
public class Interview extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Sample>samples;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Sample> getSamples() {
        return samples;
    }

    public void setSamples(RealmList<Sample> samples) {
        this.samples = samples;
    }

}
