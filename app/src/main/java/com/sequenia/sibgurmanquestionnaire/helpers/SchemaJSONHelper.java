package com.sequenia.sibgurmanquestionnaire.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.google.android.gms.maps.GoogleMap;
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

import io.realm.RealmList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class SchemaJSONHelper {

    Context context;
    private ArrayList<Sample>samples;
    private ArrayList<Question>questions;
    private ArrayList<Answerd>answerds;
    private ArrayList<AnswerdTypeFree>typeFrees;
    private ArrayList<AnswerdTypeRaing>typeRaings;
    private ArrayList<AnswerdTypeSelect>typeSelects;
    private ArrayList<AnswerdTypeTranslate>typeTranslates;
    ArrayList<Interview> interviews;

    public SchemaJSONHelper(Context context){
        this.context=context;
        samples=new ArrayList<>(DatabeseHelpers.getSample(context));
        answerds = new ArrayList<>(DatabeseHelpers.getAnswered(context));
    }

    public String anserdSchema(int idQuestionary){
        String resultString=null;
        JSONObject jsonObjectResult=new JSONObject();
        //jsonObjectResult.put("id",idQuestionary);
        return resultString;
    }
}
