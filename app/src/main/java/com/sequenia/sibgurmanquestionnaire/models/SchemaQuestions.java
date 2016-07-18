package com.sequenia.sibgurmanquestionnaire.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan1 on 13.07.2016.
 */
public class SchemaQuestions extends RealmObject{

    @PrimaryKey
    private int id;
    private String schema;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
