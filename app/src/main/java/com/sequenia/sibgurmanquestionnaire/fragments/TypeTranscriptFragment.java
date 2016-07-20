package com.sequenia.sibgurmanquestionnaire.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.adapters.FreeQuestAdapter;
import com.sequenia.sibgurmanquestionnaire.adapters.TranslateAdapter;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeTranscriptFragment extends Fragment{

    private TextView nameQuestion;
    private RecyclerView recyclerView;
    private FreeQuestAdapter freeQuestAdapter;
    private ArrayList<Sample> sampleAns;

    private static final String ARG_TRANSLATE="com.sequenia.sibgurmanquestionnaire.fragments.translate";

    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";
    private static final String ARG_QUESIOTN = "com.sequenia.sibgurmanquestionnaire.fragments.question";
    private static final String ARG_INTERVIEW = "com.sequenia.sibgurmanquestionnaire.fragments.interview";
    public static TypeTranscriptFragment newInstance(TypeTranslate typeTranslate,Question question,int interview){
        TypeTranscriptFragment transcriptFragment=new TypeTranscriptFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_TYPE_QUEST,(Serializable)typeTranslate);

        bundle.putSerializable(ARG_QUESIOTN,(Serializable)question);
        bundle.putInt(ARG_INTERVIEW,interview);
        transcriptFragment.setArguments(bundle);
        return transcriptFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_transcripript_fragment,container,false);

        Bundle bundle = getArguments();
        Question question = (Question)bundle.getSerializable(ARG_QUESIOTN);
        TypeTranslate typeTranslate = (TypeTranslate)bundle.get(ARG_TYPE_QUEST);
        ArrayList<Sample> samples= new ArrayList<>(DatabeseHelpers.getSample(getActivity()));
        int interview = bundle.getInt(ARG_INTERVIEW);
        nameQuestion = (TextView)view.findViewById(R.id.name_question);
        recyclerView=(RecyclerView)view.findViewById(R.id.trans_recycler);
        TranslateAdapter translateAdapter = new TranslateAdapter(getActivity(),question,samples,typeTranslate,interview);

        nameQuestion.setText(question.getName());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(translateAdapter);

        return view;
    }
}
