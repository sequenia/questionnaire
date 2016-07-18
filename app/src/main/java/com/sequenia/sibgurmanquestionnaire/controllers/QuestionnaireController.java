package com.sequenia.sibgurmanquestionnaire.controllers;

import android.content.Context;

import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.helpers.JSONAnswerParse;
import com.sequenia.sibgurmanquestionnaire.helpers.JSONQuestionsParse;
import com.sequenia.sibgurmanquestionnaire.helpers.SchemaJSONHelper;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Questionnaire;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.TypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import org.json.JSONObject;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

/**
 * Created by ivan1 on 06.07.2016.
 */
public class QuestionnaireController {

    private Context context;
    private ArrayList<Sample>samples;
    private ArrayList<Question>questions;
    private ArrayList<TypeFree>typeFrees;
    private ArrayList<TypeRaing>typeRaings;
    private ArrayList<TypeSelect>typeSelects;
    private ArrayList<TypeTranslate>typeTranslates;

    private ArrayList<Sample>answerSample;
    private ArrayList<AnswerdTypeRaing>answerdTypeRaings;
    private ArrayList<AnswerdTypeFree>answerdTypeFrees;
    private ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    private ArrayList<AnswerdTypeSelect>answerdTypeSelects;
    private ArrayList<Answerd>answerds;
    private SchemaJSONHelper schemaJSONHelper;

    public QuestionnaireController(Context context){
        this.context=context;
    }

    public void loadAnswerd(){

    }

    public void loadQuestionnaire(){
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setId(1);
        DatabeseHelpers.updateSchemaQuestion(context,(DatabeseHelpers.jsonDebug));
        int i = DatabeseHelpers.getIdSchemaQuestion(context);
        questionnaire.setSchemaQuestions(i);

        JSONQuestionsParse jsonQuestionsParse=new JSONQuestionsParse();
        jsonQuestionsParse.parse(DatabeseHelpers.jsonDebug);
        samples=jsonQuestionsParse.getSamples();
        questions=jsonQuestionsParse.getQuestions();
        typeFrees=jsonQuestionsParse.getTypeFrees();
        typeRaings=jsonQuestionsParse.getTypeRaings();
        typeSelects=jsonQuestionsParse.getTypeSelects();
        typeTranslates=jsonQuestionsParse.getTypeTranslates();
        schemaJSONHelper = new SchemaJSONHelper(context,samples,questions,typeRaings,typeFrees,typeSelects,typeTranslates);
        String jsonStr=  schemaJSONHelper.createJSONAnswer(1,1);

        questionnaire.setSchemaAnswer(jsonStr);
        questionnaire.setActual(false);
        questionnaire.setAnswered(false);
        questionnaire.setDeparture(false);

        DatabeseHelpers.updateQuestionnaire(context, questionnaire);

        JSONAnswerParse jsonAnswerParse = new JSONAnswerParse();
        jsonAnswerParse.parse(jsonStr);

        answerSample=jsonAnswerParse.getSamples();
        //DatabeseHelpers.updateQuestionnaire(context,questionnaire);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public ArrayList<Sample>getSamples(){ return samples;}

    public ArrayList<TypeFree> getTypeFrees() {
        return typeFrees;
    }

    public ArrayList<TypeRaing> getTypeRaings() {
        return typeRaings;
    }

    public ArrayList<TypeSelect> getTypeSelects() {
        return typeSelects;
    }

    public ArrayList<TypeTranslate> getTypeTranslates() {
        return typeTranslates;
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

    public ArrayList<Sample> getAnswerSample() {
        return answerSample;
    }
}
