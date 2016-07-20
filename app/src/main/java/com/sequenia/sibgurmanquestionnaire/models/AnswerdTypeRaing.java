package com.sequenia.sibgurmanquestionnaire.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ivan1 on 13.07.2016.
 */
public class AnswerdTypeRaing extends RealmObject {

    @PrimaryKey
    int answ_id;

    public int getAnsw_id() {
        return answ_id;
    }

    public void setAnsw_id(int answ_id) {
        this.answ_id = answ_id;
    }
    int id;
    int simple_id;
    int interview_id;
    int quest_id;
    int answered;
    boolean isAnswerd;

    public boolean isAnswerd() {
        return isAnswerd;
    }

    public void setIsAnswerd(boolean answerd) {
        isAnswerd = answerd;
    }
    public int getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(int interview_id) {
        this.interview_id = interview_id;
    }
    public int getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(int quest_id) {
        this.quest_id = quest_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSimple_id() {
        return simple_id;
    }

    public void setSimple_id(int simple_id) {
        this.simple_id = simple_id;
    }

    public int getAnswered() {
        return answered;
    }

    public void setAnswered(int answered) {
        this.answered = answered;
    }
}
