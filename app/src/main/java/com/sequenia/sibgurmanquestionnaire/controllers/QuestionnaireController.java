package com.sequenia.sibgurmanquestionnaire.controllers;

import android.content.Context;

import com.sequenia.sibgurmanquestionnaire.helpers.AnswerdHelper;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.helpers.JSONAnswerParse;
import com.sequenia.sibgurmanquestionnaire.helpers.JSONQuestionsParse;
import com.sequenia.sibgurmanquestionnaire.helpers.SchemaJSONHelper;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Interview;
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

import io.realm.RealmList;

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

    private JSONQuestionsParse jsonQuestionsParse;
    private Questionnaire questionnaire;
    public QuestionnaireController(Context context){

        this.jsonQuestionsParse=new JSONQuestionsParse();

        this.context=context;
    }


    public void loadQuestionnaire() {
        this.jsonQuestionsParse.parse(context,DatabeseHelpers.jsonDebug);
       // this.jsonQuestionsParse.sample(context);
        DatabeseHelpers.updateSchemaQuestion(context, (DatabeseHelpers.jsonDebug));
    }

    private Interview createInterview(int id){
        Interview interview = new Interview();
        interview.setId(id);
        ArrayList<Sample>samplesForInterview=new ArrayList<>(DatabeseHelpers.getSampleByIDQuestionary(context,id));
        RealmList<Sample>sampleRealmList=new RealmList<>();
        for (Sample sample:samplesForInterview)
        {
            Sample tmp=new Sample();
            tmp.setId(sample.getId());
            tmp.setName(sample.getName());
            tmp.setAnswerd(sample.getAnswerd());
            sampleRealmList.add(tmp);
        }
        interview.setSamples(sampleRealmList);

        return interview;
    }

    public void createQuestionnaire(int id){

        int idSchema=DatabeseHelpers.getIdSchemaQuestion(context);
        jsonQuestionsParse.sample(context);
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(id);
        questionnaire.setSchemaQuestions(idSchema);
        questionnaire.setDeparture(false);
        questionnaire.setAnswered(false);
        questionnaire.setActual(true);
        questionnaire.setInterviews(createInterview(questionnaire.getId()));

        workJson();
        DatabeseHelpers.updateQuestionnaire(context,questionnaire);
        this.questionnaire=questionnaire;

    }
    public void updateQuestionnaires(int id) {
        questionnaire = DatabeseHelpers.getLastActualQuestionnaires(context);
        Questionnaire questionnaireResult=new Questionnaire();
        if (questionnaire==null){
            jsonQuestionsParse.sample(context);
            int idSchema=DatabeseHelpers.getIdSchemaQuestion(context);
            questionnaireResult.setId(id);
            questionnaireResult.setSchemaQuestions(idSchema);
            questionnaireResult.setDeparture(false);
            questionnaireResult.setAnswered(false);
            questionnaireResult.setActual(true);
            questionnaireResult.setInterviews(createInterview(questionnaire.getId()));

            workJson();
                    //  int idQuestionnaire = questionnaire.getId();
            DatabeseHelpers.updateQuestionnaire(context,questionnaireResult);
        }

        else {
            //questionnaire = DatabeseHelpers.getLastActualQuestionnaires(context);
            workJson();
        }
    }

    private void workJson(){

        samples=jsonQuestionsParse.getSamples();
        questions=jsonQuestionsParse.getQuestions();
        typeFrees=jsonQuestionsParse.getTypeFrees();
        typeRaings=jsonQuestionsParse.getTypeRaings();
        typeSelects=jsonQuestionsParse.getTypeSelects();
        typeTranslates=jsonQuestionsParse.getTypeTranslates();

    }

    public Questionnaire getActual(){
        return questionnaire;
    }
    public ArrayList<Question> getQuestions() {
        for (Question question:questions){
            switch (question.getType()){
                case 1:
                    ArrayList<AnswerdTypeRaing> answerdTypeRaings=new ArrayList<>(DatabeseHelpers.getAnsweredTypeRaing(context,question.getId(),questionnaire.getId()));
                    question.setAnswered(true);
                    for (AnswerdTypeRaing answerdTypeRaing:answerdTypeRaings){

                        if (!answerdTypeRaing.isAnswerd()){
                            question.setAnswered(false);
                            break;
                        }
                    }
                    break;
                case 2:
                    ArrayList<AnswerdTypeFree> answerdTypeFrees=new ArrayList<>(DatabeseHelpers.getAnsweredTypeFree(context,question.getId(),questionnaire.getId()));
                    question.setAnswered(true);
                    for (AnswerdTypeFree answerdTypeFree:answerdTypeFrees){
                        if (!answerdTypeFree.isAnswerd()) {
                            question.setAnswered(false);
                            break;
                        }
                    }
                    break;
                case 3:
                    ArrayList<AnswerdTypeTranslate> answerdTypeTranslates=new ArrayList<>(DatabeseHelpers.getAnswerdTypeTranslate(context,question.getId(),questionnaire.getId()));
                    question.setAnswered(true);
                    for (AnswerdTypeTranslate answerdTypeTranslate:answerdTypeTranslates){
                        if (!answerdTypeTranslate.isAnswerd()) {
                            question.setAnswered(false);
                            break;
                        }
                    }
                    break;
                case 4:
                    ArrayList<AnswerdTypeSelect> answerdTypeSelects=new ArrayList<>(DatabeseHelpers.getAnswerdTypeSelect(context,question.getId(),questionnaire.getId()));
                    question.setAnswered(true);
                    for (AnswerdTypeSelect answerdTypeSelect:answerdTypeSelects){
                        if (!answerdTypeSelect.isAnswerd()) {
                            question.setAnswered(false);
                            break;
                        }
                    }
                    break;

            }

        }

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

    public ArrayList<Sample> getAnswerSample() {
        return new ArrayList<>(DatabeseHelpers.getSample(context));
    }
}
