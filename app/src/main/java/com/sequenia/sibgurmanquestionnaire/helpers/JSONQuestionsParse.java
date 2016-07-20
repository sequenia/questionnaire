package com.sequenia.sibgurmanquestionnaire.helpers;

import android.content.Context;

import com.sequenia.sibgurmanquestionnaire.fragments.SelectedFragment;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Interview;
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

import io.realm.RealmList;

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

    private ArrayList<Sample>answerdSamples;
    private ArrayList<Answerd>answerds;
    private ArrayList<AnswerdTypeFree>answerdTypeFrees;
    private ArrayList<AnswerdTypeRaing>answerdTypeRaings;
    private ArrayList<AnswerdTypeSelect>answerdTypeSelects;
    private ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    private ArrayList<Interview> interviews;
    private JSONArray jsonArraySample;
    private int idQuestionary;



    public void sample(Context context){
        try {
        DatabeseHelpers.updateAnswerd(context,answerds);

        ArrayList<Answerd>answerds = new ArrayList<>(DatabeseHelpers.getAnswered(context));
        answerdSamples = new ArrayList<>();
        answerdTypeFrees=new ArrayList<>();
        answerdTypeRaings=new ArrayList<>();
        answerdTypeTranslates=new ArrayList<>();
        answerdTypeSelects = new ArrayList<>();
        for (int i = 0; i < jsonArraySample.length(); i++) {
            Sample sample = new Sample();
            for (Answerd answerdSimple : answerds) {


                JSONObject jsonSample = (JSONObject) jsonArraySample.get(i);

                sample.setId(jsonSample.getInt(ID));

                sample.setName(jsonSample.getString(NAME));
                sample.setIdQuestionary(idQuestionary);
                ArrayList<Answerd> answerdsForInterview = new ArrayList<>(DatabeseHelpers.getAnswered(context));
                RealmList<Answerd> answerdsRealmList = new RealmList<>();
                for (Answerd answerd1 : answerdsForInterview) {
                    Answerd tmp = new Answerd();
                    tmp.setId(answerd1.getId());
                    tmp.setName(answerd1.getName());
                    tmp.setType(answerd1.getType());
                    answerdsRealmList.add(tmp);
                }


                switch (answerdSimple.getType()) {
                    case 1:
                            AnswerdTypeRaing answerdTypeRaing = new AnswerdTypeRaing();
                            int id=answerdSimple.getId();
                            int sample_id=sample.getId();
                            answerdTypeRaing.setAnsw_id(Integer.parseInt(idQuestionary+""+sample_id+""+id));
                            answerdTypeRaing.setId(id);
                            answerdTypeRaing.setIsAnswerd(false);
                            answerdTypeRaing.setInterview_id(idQuestionary);
                            answerdTypeRaing.setSimple_id(sample_id);
                            answerdTypeRaing.setAnswered(0);
                            answerdTypeRaings.add(answerdTypeRaing);


                            break;
                        case 2:
                            AnswerdTypeFree answerdTypeFree = new AnswerdTypeFree();
                            int id1=answerdSimple.getId();
                            int sample_id1=sample.getId();
                            answerdTypeFree.setAnsw_id(Integer.parseInt(idQuestionary+""+sample_id1+""+id1));
                            answerdTypeFree.setId(id1);
                            answerdTypeFree.setIsAnswerd(false);
                            answerdTypeFree.setInterview_id(idQuestionary);
                            answerdTypeFree.setSample_id(sample_id1);
                            answerdTypeFree.setPositive("");
                            answerdTypeFree.setNegative("");
                            answerdTypeFrees.add(answerdTypeFree);

                            break;
                        case 3:
                            AnswerdTypeTranslate answerdTypeTranslate = new AnswerdTypeTranslate();
                            int id2=answerdSimple.getId();
                            int sample_id2=sample.getId();
                            answerdTypeTranslate.setAnsw_id(Integer.parseInt(idQuestionary+""+sample_id2+""+id2));
                            answerdTypeTranslate.setId(id2);
                            answerdTypeTranslate.setIsAnswerd(false);
                            answerdTypeTranslate.setInterview_id(idQuestionary);
                            answerdTypeTranslate.setSimple_id(sample_id2);
                            answerdTypeTranslate.setAnswered(0);
                            answerdTypeTranslates.add(answerdTypeTranslate);


                            break;
                        case 4:

                            AnswerdTypeSelect answerdTypeSelect = new AnswerdTypeSelect();
                            int id3=answerdSimple.getId();
                            int sample_id3=sample.getId();
                            answerdTypeSelect.setAnsw_id(Integer.parseInt(idQuestionary+""+sample_id3+""+id3));
                            answerdTypeSelect.setId(id3);
                            answerdTypeSelect.setIsAnswerd(false);
                            answerdTypeSelect.setInterview_id(idQuestionary);
                            answerdTypeSelect.setSimple_id(sample_id3);
                            answerdTypeSelect.setAnswered(false);
                            answerdTypeSelects.add(answerdTypeSelect);


                            break;

                }
                sample.setAnswerd(answerdsRealmList);

            }
            samples.add(sample);

        }
            DatabeseHelpers.updateAnswerdTypeRaing(context, answerdTypeRaings);
            DatabeseHelpers.updateAnswerdTypeTranslate(context, answerdTypeTranslates);
            DatabeseHelpers.updateAnswerdTypeSelected(context, answerdTypeSelects);
            DatabeseHelpers.updateAnswerTypeFree(context, answerdTypeFrees);
        DatabeseHelpers.updateSample(context, samples);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parse(Context context, String jsonString){
        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            JSONObject questionnaireJSON = (JSONObject) jsonObject.get(QUESTIONNAIRE);
            idQuestionary=questionnaireJSON.getInt(ID);
            jsonArraySample = (JSONArray)questionnaireJSON.get(SAMPLES);
            typeFrees = new ArrayList<>();
            typeRaings = new ArrayList<>();
            typeSelects = new ArrayList<>();
            typeTranslates = new ArrayList<>();

            answerds = new ArrayList<>();
            interviews=new ArrayList<>();
            Answerd answerd;
            samples = new ArrayList<>();
            JSONObject jsonQuestion;


            JSONArray jsonArrayQuestions = (JSONArray)questionnaireJSON.get(QUESTIONS);
            questions = new ArrayList<>();
            int size=jsonArrayQuestions.length();
            Question question;

            for (int i=0; i<size;i++){
                question = new Question();
                jsonQuestion = (JSONObject)jsonArrayQuestions.get(i);
                question.setId(jsonQuestion.getInt(ID));
                question.setName(jsonQuestion.getString(NAME));
                question.setRequired(jsonQuestion.getBoolean(ISREQUIRED));
                question.setAnswered(jsonQuestion.getBoolean(ISANSWERED));
                int type=jsonQuestion.getInt(TYPE);
                answerd =new Answerd();
                answerd.setId(jsonQuestion.getInt(ID));
                answerd.setName(jsonQuestion.getString(NAME));
                answerd.setType(type);

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

                        DatabeseHelpers.updateAnswerTypeFree(context,answerdTypeFrees);
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
                answerds.add(answerd);


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
