package com.sequenia.sibgurmanquestionnaire.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.google.android.gms.maps.GoogleMap;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.TypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;
import com.sequenia.sibgurmanquestionnaire.services.GPSTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class SchemaJSONHelper {

    Context context;
    private ArrayList<Sample>samples;
    private ArrayList<Question>questions;
    private ArrayList<TypeFree>typeFrees;
    private ArrayList<TypeRaing>typeRaings;
    private ArrayList<TypeSelect>typeSelects;
    private ArrayList<TypeTranslate>typeTranslates;
    String jsonDebug="{\n" +
            "  \"questionnaire\": {\n" +
            "    \"id\": 1,\n" +
            "    \"samples\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Образец Р\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Образец С\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"name\": \"Образец Н\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"Questions\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"1. Насколько Вым В ЦЕЛОМ понравился ВКУС пельменей:\",\n" +
            "        \"comment\": \"1 - Совсем не нравится вкус в целом, 4 - Не то, чтобы понравилось, не то, чтобы не понравилось, 7 - Очень понравился вкус в целом\",\n" +
            "        \"minTranscript\": \"Совсем не понравился ВКУС ПЕЛЬМЕНЕЙ В ЦЕЛОМ\",\n" +
            "        \"maxTranscript\": \"Очень понравился ВКУС ПЕЛЬМЕНЕЙ В ЦЕЛОМ\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": true\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Что именно вам ПОНРАВИЛОСЬ или НЕ ПОНРАВИЛОСЬ в пельменях:\",\n" +
            "        \"positive\": \"ЧТО ПОНРАВИЛОСЬ\",\n" +
            "        \"negative\": \"ЧТО НЕ ПОНРАВИЛОСЬ\",\n" +
            "        \"type\": 2,\n" +
            "        \"isRequired\": true\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"name\": \"3. Оцените, пожалуйста, насколько Вам понравился ВНЕШНИЙ ВИД ГОТОВЫХ пельменей:\",\n" +
            "        \"comment\": \"1 - Совсем не нравится внешний вид, 4 - Не то, чтобы понравилось, не то, чтобы не понравилось, 7 - Очень понравился внешний вид\",\n" +
            "        \"minTranscript\": \"Совсем не понравился ВНЕШНИЙ ВИД ГОТОВЫХ пельней\",\n" +
            "        \"maxTranscript\": \"Очень понравился ВНЕШНИЙ ВИД ГОТОВЫХ пельней\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": true\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"name\": \"4. Оцените, пожалуйста, насколько ВКУСНЫМ Вам показалось ТЕСТО у этих пельменей:\",\n" +
            "        \"comment\": \"1 - Совсем не вкусное тесто, 4 - Не то, чтобы вкусное тесто, не то, чтобы не вкусное тесто, 7 - Очень вкусное тесто\",\n" +
            "        \"minTranscript\": \"Совсем не вкусное тесто\",\n" +
            "        \"maxTranscript\": \"Очень вкусное тесто\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": true\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 5,\n" +
            "        \"name\": \"5. Как бы Вы оценили ПЛОТНОСТЬ НАЧИНКИ пельменей:\",\n" +
            "        \"variant_answerds\": {\n" +
            "          \"1\": \"Совсем не плотная/мягка начинка\",\n" +
            "          \"2\": \"Менее плотная начинка, чем нужно\",\n" +
            "          \"3\": \"Как раз такая плотность начинки, и нужно\",\n" +
            "          \"4\": \"Более плотная начинка, чем нужно\",\n" +
            "          \"5\": \"Очень плотна/жеская начинка\"\n" +
            "        },\n" +
            "        \"type\": 3,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 6,\n" +
            "        \"name\": \"6. Как бы Вы оценили ПЛОТНОСТЬ НАЧИНКИ пельменей:\",\n" +
            "        \"variant_answerds\": {\n" +
            "          \"1\": \"Совсем не сочная/сухая начинка\",\n" +
            "          \"2\": \"Менее сочная начинка, чем нужно\",\n" +
            "          \"3\": \"Как раз такая сочность начинки, как нужно\",\n" +
            "          \"4\": \"Более сочная начинка, чем нужно\",\n" +
            "          \"5\": \"Слишком сочная начинка\"\n" +
            "        },\n" +
            "        \"type\": 3,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 7,\n" +
            "        \"name\": \"7. Насколько Вам понравился ВКУС НАЧИНКИ в пельменях:\",\n" +
            "        \"comment\": \"1 - Совсем не понравился мясной вкус, 4 - Не то, чтобы понравился, не то, чтобы не поравилось, 7 - Очень понравился мясной вкусной\",\n" +
            "        \"minTranscript\": \"Совсем не понравился ВКУС начинки в пельменях\",\n" +
            "        \"maxTranscript\": \"Очень понравился ВКУС начинки в пельменях\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 8,\n" +
            "        \"name\": \"8. 1. Оценитt, насколько у этих пельменей ВЫРАЖЕН МЯСНОЙ ВКУС в начинке:\",\n" +
            "        \"comment\": \"1 - Совершенно не выражен мясной вкус, 4 - Средняя степень выраженности мясного вкуса, 7 - Яркий/выраженный мясной вкус\",\n" +
            "        \"minTranscript\": \"Совсем нет мясного вкуса/мясной вкус выражен очень слабо\",\n" +
            "        \"maxTranscript\": \"Мясной вкус выражен хорошо/яркий мясной вкус\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 9,\n" +
            "        \"name\": \"9. Оценить, пожалуйста, КОЛИЧЕСТВО НАЧИНКИ в этих пельменей:\",\n" +
            "        \"comment\": \"1 - Начинки очень мало, 4 - Средняя количество начинки: не то, чтоб много, не то, чтоб иало, 7 - Начинки много\",\n" +
            "        \"minTranscript\": \"Начинки очень мало\",\n" +
            "        \"maxTranscript\": \"Начинки очень много\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\": 1,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 10,\n" +
            "        \"name\": \"10. Как бы Вы оценили СООТНОШЕНИЕ ТЕСТА И НАЧИНКИ в пельменях:\",\n" +
            "        \"variant_answerds\": {\n" +
            "          \"1\": \"Теста намного больше, чем нужно\",\n" +
            "          \"2\": \"Теста немного больше, чем нужно\",\n" +
            "          \"3\": \"Оптимальное сочитание теста и начинки\",\n" +
            "          \"4\": \"Начинки немного больше, чем нужно\",\n" +
            "          \"5\": \"Начинки намного больше, чем нужно\"\n" +
            "        },\n" +
            "        \"type\": 3,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 11,\n" +
            "        \"name\": \"11. Как бы Вы оценили УРОВЕНЬ СОЛЕНОСТИ пельменей:\",\n" +
            "        \"comment\": \"1 - Совсем не соленые, 4 - Умеренная соленость, 7-Очеть соленые\",\n" +
            "        \"minTranscript\": \"Совсем не соленые\",\n" +
            "        \"maxTranscript\": \"Очень соленые\",\n" +
            "        \"min\": 1,\n" +
            "        \"max\": 7,\n" +
            "        \"degres\": 1,\n" +
            "        \"type\":1,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 12,\n" +
            "        \"name\": \"12. Какой из образцов пельменей Вам понравился по вкусовым качествам в целом больше всего?\",\n" +
            "        \"type\": 4,\n" +
            "        \"isRequired\": false\n" +
            "        \"isAnswered\": false\n"+
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    public SchemaJSONHelper(Context context, ArrayList<Sample> samples, ArrayList<Question> questions,ArrayList<TypeRaing>typeRaings, ArrayList<TypeFree>typeFrees,
                            ArrayList<TypeSelect>typeSelects, ArrayList<TypeTranslate>typeTranslates){
        this.samples=samples;
        this.questions=questions;
        this.typeFrees=typeFrees;
        this.typeSelects=typeSelects;
        this.typeRaings=typeRaings;
        this.typeTranslates=typeTranslates;
        this.context = context;
    }

    public String createJSONAnswer(int id_questionnaire, int id_user){
        JSONObject rootJson = new JSONObject();
        JSONObject resultJson = new JSONObject();
        GPSTracker gpsTracker=new GPSTracker(context);
        double longtude=gpsTracker.getLongitude();
        double latitude=gpsTracker.getLatitude();
        Locale locale = Locale.getDefault();
        Geocoder geocoder=new Geocoder(context,locale);

        try {
            List<Address> addresses=geocoder.getFromLocation(latitude,longtude,1);
           // String addressString=addresses.get(3)+","+addresses.get(2)+","+addresses.get(1)+","+addresses.get(0);
            resultJson.put("id",id_questionnaire);
            resultJson.put("id_user",id_user);
            resultJson.put("FIO","Пупкин Вася");
            resultJson.put("Address","");
            JSONArray jsonArraySimples=new JSONArray();
            for (int i=0; i<samples.size();i++) {
                JSONObject jsonObjectSimple=new JSONObject();
                int id =  samples.get(i).getId();
                String name= samples.get(i).getName();
                JSONArray jsonArrayQuestions=new JSONArray();
                for (int j=0;j<questions.size(); j++){
                    JSONObject jsonObjectAnswer=new JSONObject();
                    Question question=questions.get(j);
                    switch (question.getType()){
                        case 1:
                            jsonObjectAnswer.put("id",question.getId());
                            jsonObjectAnswer.put("name",question.getName());
                            jsonObjectAnswer.put("type",1);
                            jsonObjectAnswer.put("answerd",0);
                            jsonObjectAnswer.put("isAnswerd",false);
                            jsonArrayQuestions.put(jsonObjectAnswer);
                            break;
                        case 2:
                            jsonObjectAnswer.put("id",question.getId());
                            jsonObjectAnswer.put("name",question.getName());
                            jsonObjectAnswer.put("type",2);
                            jsonObjectAnswer.put("positive_answer","");
                            jsonObjectAnswer.put("negative_answer","");
                            jsonObjectAnswer.put("isAnswerd",false);
                            jsonArrayQuestions.put(jsonObjectAnswer);
                            break;
                        case 3:
                            jsonObjectAnswer.put("id",question.getId());
                            jsonObjectAnswer.put("name",question.getName());
                            jsonObjectAnswer.put("type",3);
                            jsonObjectAnswer.put("answerd",0);
                            jsonObjectAnswer.put("isAnswerd",false);
                            jsonArrayQuestions.put(jsonObjectAnswer);
                            break;
                        case 4:
                            jsonObjectAnswer.put("id",question.getId());
                            jsonObjectAnswer.put("name",question.getName());
                            jsonObjectAnswer.put("type",4);
                            jsonObjectAnswer.put("answerd",false);
                            jsonObjectAnswer.put("isAnswerd",false);
                            jsonArrayQuestions.put(jsonObjectAnswer);
                            break;
                    }

                }

                jsonObjectSimple.put("id",id);
                jsonObjectSimple.put("name",name);
                jsonObjectSimple.put("Answers",jsonArrayQuestions);
                jsonArraySimples.put(jsonObjectSimple);

            }
            resultJson.put("samples",jsonArraySimples);

            rootJson.put("answering",resultJson);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result=""+rootJson;
        return result;

    }
}
