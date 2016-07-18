package com.sequenia.sibgurmanquestionnaire.models;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeTranslate implements Serializable{

    int id;
    int id_question;
    HashMap<Integer,String> variants;


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

    public HashMap<Integer, String> getVariants() {
        return variants;
    }

    public void setVariants(HashMap<Integer, String> variants) {
        this.variants = variants;
    }
}
