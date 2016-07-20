package com.sequenia.sibgurmanquestionnaire.helpers;

import android.content.Context;

import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Questionnaire;
import com.sequenia.sibgurmanquestionnaire.models.Sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ivan1 on 19.07.2016.
 */
public class AnswerdHelper {



    public static void createAnserDefault(Context context,String jsonString){
        try {
            JSONObject jsonObject= new JSONObject(jsonString);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
