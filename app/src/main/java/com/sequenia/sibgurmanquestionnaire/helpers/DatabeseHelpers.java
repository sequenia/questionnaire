package com.sequenia.sibgurmanquestionnaire.helpers;

import android.content.Context;

import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Interview;
import com.sequenia.sibgurmanquestionnaire.models.Migration;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Questionnaire;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.SchemaQuestions;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by ivan1 on 06.07.2016.
 */
public class DatabeseHelpers {
    private static boolean configurationCreated = false;
    public static Realm getRealm(Context context){
        if (!configurationCreated){
            RealmConfiguration realmConfig = new  RealmConfiguration.Builder(context)
                    .name("sibGurmanQuestionnaire.realm")
                    .schemaVersion(1)
                    .migration(new Migration())
                    .build();

            Realm.setDefaultConfiguration(realmConfig);
            configurationCreated=true;
        }
        return Realm.getDefaultInstance();
    }

    public static String jsonDebug="{\n" +
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
            "        \"isRequired\": true,\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Что именно вам ПОНРАВИЛОСЬ или НЕ ПОНРАВИЛОСЬ в пельменях:\",\n" +
            "        \"positive\": \"ЧТО ПОНРАВИЛОСЬ\",\n" +
            "        \"negative\": \"ЧТО НЕ ПОНРАВИЛОСЬ\",\n" +
            "        \"type\": 2,\n" +
            "        \"isRequired\": true,\n" +
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
            "        \"isRequired\": true,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
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
            "        \"isRequired\": false,\n" +
            "        \"isAnswered\": false\n"+
            "      },\n" +
            "      {\n" +
            "        \"id\": 12,\n" +
            "        \"name\": \"12. Какой из образцов пельменей Вам понравился по вкусовым качествам в целом больше всего?\",\n" +
            "        \"type\": 4,\n" +
            "        \"isRequired\": false,\n" +
            "        \"isAnswered\": false\n"+
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    public static void updateQuestionnaire(Context context, Questionnaire questionnaire){

        Realm realm= getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(questionnaire);
        realm.commitTransaction();

    }

    public static void updateQuestionnaires(Context context, ArrayList<Questionnaire> questionnaires){
        Realm realm= getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(questionnaires);
        realm.commitTransaction();

    }

    public static Questionnaire getQuestionnaire(Context context,int id_questionnaire){
        return getRealm(context).where(Questionnaire.class).equalTo("id",id_questionnaire).findFirst();
    }

    public static Questionnaire getLastActualQuestionnaires(Context context){

        RealmResults<Questionnaire> questionnaires=getRealm(context).where(Questionnaire.class).
                equalTo("answered",false).
                equalTo("actual",true).
                equalTo("departure",false).findAll();

        if (questionnaires.size()==0){
            return null;
        }
        return questionnaires.get(questionnaires.size()-1);

    }
    public static RealmResults<Questionnaire> getQuestionnaires(Context context){
        return getRealm(context).where(Questionnaire.class).findAll();
    }
    public static int getSizeQuestionare(Context context){
        return getRealm(context).where(Questionnaire.class).findAll().size();
    }

    public static void updateSchemaQuestion(Context context, String jsonString){
        Realm realm= getRealm(context);
        SchemaQuestions schemaQuestions=new SchemaQuestions();
        schemaQuestions.setId(1);
        schemaQuestions.setSchema(jsonString);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(schemaQuestions);
        realm.commitTransaction();

    }

    public static int getIdSchemaQuestion(Context context) {
       RealmResults<SchemaQuestions> schemaQuestionses = getRealm(context).where(SchemaQuestions.class).findAllSorted("id", Sort.ASCENDING);
        int i = 0;
        for (SchemaQuestions schemaQuestions:schemaQuestionses){
            i=schemaQuestions.getId();
        }
        return i;
    }

    public static void updateAnswerd(Context context, ArrayList<Answerd>answerds){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerds);
        realm.commitTransaction();
    }
    public static void updateSample(Context context, ArrayList<Sample>samples){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(samples);
        realm.commitTransaction();
    }
    public static void updateAnswerTypeFree(Context context, ArrayList<AnswerdTypeFree>answerdTypeFrees){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeFrees);
        realm.commitTransaction();
    }
    public static void updateAnswerTypeFree(Context context, AnswerdTypeFree answerdTypeFrees){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeFrees);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeRaing(Context context, AnswerdTypeRaing answerdTypeRaings){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeRaings);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeRaing(Context context, ArrayList<AnswerdTypeRaing>answerdTypeRaings){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeRaings);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeSelected(Context context, ArrayList<AnswerdTypeSelect>answerdTypeSelects){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeSelects);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeSelected(Context context, AnswerdTypeSelect answerdTypeSelects){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeSelects);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeTranslate(Context context, ArrayList<AnswerdTypeTranslate>answerdTypeTranslates){
        Realm realm=getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeTranslates);
        realm.commitTransaction();
    }
    public static void updateAnswerdTypeTranslate(Context context,AnswerdTypeTranslate answerdTypeTranslates) {
        Realm realm = getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(answerdTypeTranslates);
        realm.commitTransaction();
    }

        public static RealmResults<Answerd> getAnswered(Context context){
        return getRealm(context).where(Answerd.class).findAll();
    }

    public static RealmResults<AnswerdTypeFree> getAnsweredTypeFree(Context context,int id, int interview_id){
        return getRealm(context).where(AnswerdTypeFree.class).equalTo("id",id).equalTo("interview_id",interview_id).findAll();
    }
  /*  int id;
    int simple_id;
    int quest_id;
    int interview_id;*/
    public static RealmResults<AnswerdTypeRaing> getAnsweredTypeRaing(Context context,int id, int interview_id ){
        return getRealm(context).where(AnswerdTypeRaing.class).equalTo("id",id).equalTo("interview_id",interview_id).findAll();
    }
    public static RealmResults<AnswerdTypeSelect> getAnswerdTypeSelect(Context context,int id, int interview_id){
        return getRealm(context).where(AnswerdTypeSelect.class).equalTo("id",id).equalTo("interview_id",interview_id).findAll();
    }
    public static RealmResults<AnswerdTypeTranslate> getAnswerdTypeTranslate(Context context,int id, int interview_id){
        return getRealm(context).where(AnswerdTypeTranslate.class).equalTo("id",id).equalTo("interview_id",interview_id).findAll();
    }
    public static RealmResults<Sample> getSample(Context context ){
        return getRealm(context).where(Sample.class).findAll();
    }

    public static void updateInterview(Context context, Interview interview){
        Realm realm  = getRealm(context);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(interview);
        realm.commitTransaction();

    }

    public static RealmResults<Sample> getSampleByIDQuestionary(Context context,int id){
        return getRealm(context).where(Sample.class).equalTo("idQuestionary",id).findAll();
    }

}
