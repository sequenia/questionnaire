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

    private static final String ARG_TRANSLATE="com.sequenia.sibgurmanquestionnaire.fragments.translate";
    private static final String ARG_SAMPLES="com.sequenia.sibgurmanquestionnaire.fragments.samples";
    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";
    public static TypeTranscriptFragment newInstance(Question question, ArrayList<Sample> samples, TypeTranslate typeTranslate){
        TypeTranscriptFragment transcriptFragment=new TypeTranscriptFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_TRANSLATE,(Serializable)question);
        bundle.putSerializable(ARG_TYPE_QUEST,(Serializable)typeTranslate);
        bundle.putSerializable(ARG_SAMPLES,samples);
        transcriptFragment.setArguments(bundle);
        return transcriptFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_transcripript_fragment,container,false);

        Bundle bundle = getArguments();
        Question question = (Question)bundle.getSerializable(ARG_TRANSLATE);
        TypeTranslate typeTranslate = (TypeTranslate)bundle.get(ARG_TYPE_QUEST);
        ArrayList<Sample>samples = (ArrayList<Sample>)bundle.getSerializable(ARG_SAMPLES);

        nameQuestion = (TextView)view.findViewById(R.id.name_question);
        recyclerView=(RecyclerView)view.findViewById(R.id.trans_recycler);
        TranslateAdapter translateAdapter = new TranslateAdapter(this.getActivity(),samples,typeTranslate);

        nameQuestion.setText(question.getName());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(translateAdapter);

        return view;
    }
}
