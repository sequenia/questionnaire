package com.sequenia.sibgurmanquestionnaire.helpers;

import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Sample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private static final String TYPE="type";
    private static final String ANSWERED="answerd";
    private static final String NEGATIVE="negative_answer";
    private static final String POSITIVE="positive_answer";
    private static final String ANSWERS="Answers";
    private static final String SAMPLES= "samples";


    ArrayList<AnswerdTypeRaing>answerdTypeRaings;
    ArrayList<AnswerdTypeFree>answerdTypeFrees;
    ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    ArrayList<AnswerdTypeSelect>answerdTypeSelects;
    ArrayList<Answerd>answerds;
    ArrayList<Sample>samples;




    public void parse(String jsonString){
        try {
            answerdTypeRaings=new ArrayList<>();
            answerdTypeFrees=new ArrayList<>();
            answerdTypeTranslates = new ArrayList<>();
            answerdTypeSelects = new ArrayList<>();
            answerds=new ArrayList<>();
            samples=new ArrayList<>();
            Sample sample;
            AnswerdTypeRaing answerdTypeRaing;
            AnswerdTypeTranslate answerdTypeTranslate;
            AnswerdTypeFree answerdTypeFree;
            AnswerdTypeSelect answerdTypeSelect;
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONObject answeringObject=(JSONObject)jsonObject.getJSONObject(ANSWERING);
            JSONArray arrayJSONSample=answeringObject.getJSONArray(SAMPLES);
            for (int i=0; i<arrayJSONSample.length();i++){
                sample = new Sample();
                JSONObject objectSample= arrayJSONSample.getJSONObject(i);
                sample.setId(objectSample.getInt(ID));
                sample.setName(objectSample.getString(NAME));
                JSONArray jsonArrayAnswer=objectSample.getJSONArray(ANSWERS);
                Answerd answerd;

                for (int j=0; j<jsonArrayAnswer.length();j++){
                    JSONObject jsonObjectAnswer=jsonArrayAnswer.getJSONObject(j);
                    answerd=new Answerd();
                    answerd.setId(jsonObjectAnswer.getInt(ID));
                    answerd.setName(jsonObjectAnswer.getString(NAME));
                    int type= jsonObjectAnswer.getInt(TYPE);
                    answerd.setType(type);
                    answerds.add(answerd);
                    switch (type){
                        case QuestionHelper.TYPE_RATING:
                            answerdTypeRaing= new AnswerdTypeRaing();
                            answerdTypeRaing.setId(jsonObjectAnswer.getInt(ID));
                            answerdTypeRaing.setAnswered(jsonObjectAnswer.getInt(ANSWERED));
                            answerdTypeRaings.add(answerdTypeRaing);
                            break;
                        case QuestionHelper.TYPE_FREE:
                            answerdTypeFree = new AnswerdTypeFree();
                            answerdTypeFree.setId(jsonObjectAnswer.getInt(ID));
                            answerdTypeFree.setNegative(jsonObjectAnswer.getString(NEGATIVE));
                            answerdTypeFree.setPositive(jsonObjectAnswer.getString(POSITIVE));
                            answerdTypeFrees.add(answerdTypeFree);
                            break;
                        case QuestionHelper.TYPE_TRANSCRIPT:
                            answerdTypeTranslate = new AnswerdTypeTranslate();
                            answerdTypeTranslate.setId(jsonObjectAnswer.getInt(ID));
                            answerdTypeTranslate.setAnswered(jsonObjectAnswer.getInt(ANSWERED));
                            answerdTypeTranslates.add(answerdTypeTranslate);
                            break;
                        case QuestionHelper.TYPE_SELECTED:
                            answerdTypeSelect = new AnswerdTypeSelect();
                            answerdTypeSelect.setId(jsonObjectAnswer.getInt(ID));
                            answerdTypeSelect.setAnswered(jsonObjectAnswer.getBoolean(ANSWERED));
                            answerdTypeSelects.add(answerdTypeSelect);
                            break;

                    }

                }

                sample.setAnswerd(answerds);
                samples.add(sample);


            }
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

    public ArrayList<Answerd> getAnswerds() {
        return answerds;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }
}
