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
import com.sequenia.sibgurmanquestionnaire.adapters.RaitingQuestAdapter;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeFreeFragment extends Fragment{

    private TextView nameQuestion;
    private RecyclerView recyclerView;
    private FreeQuestAdapter freeQuestAdapter;
    private ArrayList<Sample> sampleAns;
    private ArrayList<AnswerdTypeFree> answerdTypeFrees;


    private static final String ARG_FREE="com.sequenia.sibgurmanquestionnaire.fragments.free";

    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";
    private static final String ARG_QUESIOTN = "com.sequenia.sibgurmanquestionnaire.fragments.question";
    private static final String ARG_INTERVIEW = "com.sequenia.sibgurmanquestionnaire.fragments.interview";
    public static TypeFreeFragment newInstance(TypeFree typeFree,Question question,int interview){
        TypeFreeFragment freeFragment= new TypeFreeFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_TYPE_QUEST,(Serializable)typeFree);
        bundle.putSerializable(ARG_QUESIOTN,(Serializable)question);
        bundle.putInt(ARG_INTERVIEW,interview);
        freeFragment.setArguments(bundle);
        return freeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_free_fragment,container,false);

        Bundle bundle = getArguments();
        Question question=(Question)bundle.getSerializable(ARG_QUESIOTN);
        TypeFree typeFree = (TypeFree)bundle.get(ARG_TYPE_QUEST);
        ArrayList<Sample> samples= new ArrayList<>(DatabeseHelpers.getSample(getActivity()));
        int interview = bundle.getInt(ARG_INTERVIEW);

        nameQuestion = (TextView)view.findViewById(R.id.name_question);
        recyclerView = (RecyclerView)view.findViewById(R.id.free_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        nameQuestion.setText(question.getName());

        freeQuestAdapter = new FreeQuestAdapter(getActivity(),question,samples,typeFree,interview);
        recyclerView.setAdapter(freeQuestAdapter);


        return view;
    }
}
