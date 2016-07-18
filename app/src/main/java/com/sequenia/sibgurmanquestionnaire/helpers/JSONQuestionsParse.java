package com.sequenia.sibgurmanquestionnaire.helpers;

import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.TypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarEntry;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class JSONQuestionsParse {


    private static final String QUESTIONNAIRE="questionnaire";
    private static final String ID="id";
    private static final String SAMPLES="samples";
    private static final String NAME="name";
    private static final String QUESTIONS="Questions";
    private static final String COMMENT="comment";
    private static final String MINTRANSCRIPT="minTranscript";
    private static final String MAXTRANSCRIPT="maxTranscript";
    private static final String MIN="min";
    private static final String MAX="max";
    private static final String DEGRES="degres";
    private static final String TYPE="type";
    private static final String ISREQUIRED="isRequired";
    private static final String ISANSWERED="isAnswered";
    private static final String POSITIVE="positive";
    private static final String NEGATIVE="negative";
    private static final String VARIANT_ANSWERDS="variant_answerds";


    private ArrayList<Sample>samples;
    private ArrayList<Question>questions;
    private ArrayList<TypeFree>typeFrees;
    private ArrayList<TypeRaing>typeRaings;
    private ArrayList<TypeSelect>typeSelects;
    private ArrayList<TypeTranslate>typeTranslates;



    public void parse(String jsonString){
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONObject questionnaireJSON = (JSONObject) jsonObject.get(QUESTIONNAIRE);
            JSONArray jsonArraySample = (JSONArray)questionnaireJSON.get(SAMPLES);
            typeFrees = new ArrayList<>();
            typeRaings = new ArrayList<>();
            typeSelects = new ArrayList<>();
            typeTranslates = new ArrayList<>();
            samples = new ArrayList<>();
            for (int i=0; i<jsonArraySample.length();i++){
                Sample sample = new Sample();
                JSONObject jsonSample =(JSONObject) jsonArraySample.get(i);
                sample.setId(jsonSample.getInt(ID));
                sample.setName(jsonSample.getString(NAME));
                samples.add(sample);
            }

            JSONArray jsonArrayQuestions = (JSONArray)questionnaireJSON.get(QUESTIONS);
            questions = new ArrayList<>();
            int size=jsonArrayQuestions.length();
            Question question;
            JSONObject jsonQuestion;
            for (int i=0; i<size;i++){
                question = new Question();
                jsonQuestion = (JSONObject)jsonArrayQuestions.get(i);
                question.setId(jsonQuestion.getInt(ID));
                question.setName(jsonQuestion.getString(NAME));
                question.setRequired(jsonQuestion.getBoolean(ISREQUIRED));
                question.setAnswered(jsonQuestion.getBoolean(ISANSWERED));
                int type=jsonQuestion.getInt(TYPE);
                question.setType(type);

                switch (type){
                    case 1:
                        TypeRaing typeRaing = new TypeRaing();
                        typeRaing.setId(jsonQuestion.getInt(ID));
                        typeRaing.setCommit(jsonQuestion.getString(COMMENT));
                        typeRaing.setMinTranscript(jsonQuestion.getString(MINTRANSCRIPT));
                        typeRaing.setMaxTranscript(jsonQuestion.getString(MAXTRANSCRIPT));
                        typeRaing.setMin(jsonQuestion.getInt(MIN));
                        typeRaing.setMax(jsonQuestion.getInt(MAX));
                        typeRaing.setDegres(jsonQuestion.getInt(DEGRES));
                        typeRaings.add(typeRaing);
                        break;
                    case 2:
                        TypeFree typeFree= new TypeFree();
                        typeFree.setId(jsonQuestion.getInt(ID));
                        typeFree.setPositive(jsonQuestion.getString(POSITIVE));
                        typeFree.setNegative(jsonQuestion.getString(NEGATIVE));
                        typeFrees.add(typeFree);
                        break;
                    case 3:
                        TypeTranslate typeTranslate = new TypeTranslate();
                        typeTranslate.setId(jsonQuestion.getInt(ID));
                        JSONObject jsonVariantObject =(JSONObject)jsonQuestion.get(VARIANT_ANSWERDS);
                        HashMap<Integer,String>varian=new HashMap<>();
                        varian.put(1,jsonVariantObject.getString("1"));
                        varian.put(2,jsonVariantObject.getString("2"));
                        varian.put(3,jsonVariantObject.getString("3"));
                        varian.put(4,jsonVariantObject.getString("4"));
                        varian.put(5,jsonVariantObject.getString("5"));
                        typeTranslate.setVariants(varian);
                        typeTranslates.add(typeTranslate);
                        break;
                    case 4:
                        TypeSelect typeSelect = new TypeSelect();
                        typeSelect.setId(jsonQuestion.getInt(ID));
                        typeSelects.add(typeSelect);
                        break;
                }
                questions.add(question);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sample>getSamples(){
        return samples;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

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
}
