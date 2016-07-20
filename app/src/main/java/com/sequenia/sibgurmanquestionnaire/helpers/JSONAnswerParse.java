package com.sequenia.sibgurmanquestionnaire.helpers;

import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Interview;
import com.sequenia.sibgurmanquestionnaire.models.Questionnaire;
import com.sequenia.sibgurmanquestionnaire.models.Sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class JSONAnswerParse {
    private static final String FIO="FIO";
    private static final String QUESTIONNAIRE="questionnaire";
    private static final String ANSWERING = "answering";
    private static final String ADDRESS="address";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String SAMPLE_ID="sample_id";
    private static final String TYPE="type";
    private static final String ANSWERED="answerd";
    private static final String NEGATIVE="negative_answer";
    private static final String POSITIVE="positive_answer";
    private static final String ANSWERS="Answers";
    private static final String SAMPLES= "samples";
    private static final String INTERVIEW="interview";


    ArrayList<AnswerdTypeRaing>answerdTypeRaings;
    ArrayList<AnswerdTypeFree>answerdTypeFrees;
    ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    ArrayList<AnswerdTypeSelect>answerdTypeSelects;
    ArrayList<Sample>samples;
    ArrayList<Interview>interviews;
    RealmList<Answerd>answerds;




    public void parse(String jsonString){
        try {

            answerdTypeRaings=new ArrayList<>();
            answerdTypeFrees=new ArrayList<>();
            answerdTypeTranslates = new ArrayList<>();
            answerdTypeSelects = new ArrayList<>();
            interviews=new ArrayList<>();

            samples=new ArrayList<>();
            Sample sample;
            AnswerdTypeRaing answerdTypeRaing;
            AnswerdTypeTranslate answerdTypeTranslate;
            AnswerdTypeFree answerdTypeFree;
            AnswerdTypeSelect answerdTypeSelect;
            Interview interview;
            Questionnaire questionnaire;
            JSONObject jsonObject = new JSONObject(jsonString);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<AnswerdTypeRaing> getAnswerdTypeRaings() {
        return answerdTypeRaings;
    }

    public ArrayList<AnswerdTypeFree> getAnswerdTypeFrees() {
        return answerdTypeFrees;
    }

    public ArrayList<AnswerdTypeTranslate> getAnswerdTypeTranslates() {
        return answerdTypeTranslates;
    }

    public ArrayList<AnswerdTypeSelect> getAnswerdTypeSelects() {
        return answerdTypeSelects;
    }

    public RealmList<Answerd> getAnswerds() {
        return answerds;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }
}
