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

    private static final String ARG_FREE="com.sequenia.sibgurmanquestionnaire.fragments.free";
    private static final String ARG_SAMPLES="com.sequenia.sibgurmanquestionnaire.fragments.samples";
    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";
    public static TypeFreeFragment newInstance(Question question, ArrayList<Sample>samples, TypeFree typeFree){
        TypeFreeFragment freeFragment= new TypeFreeFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_FREE,(Serializable)question);
        bundle.putSerializable(ARG_TYPE_QUEST,(Serializable)typeFree);
        bundle.putSerializable(ARG_SAMPLES,samples);
        freeFragment.setArguments(bundle);
        return freeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_free_fragment,container,false);

        Bundle bundle = getArguments();
        Question question=(Question)bundle.getSerializable(ARG_FREE);
        TypeFree typeFree = (TypeFree)bundle.get(ARG_TYPE_QUEST);
        ArrayList<Sample>samples = (ArrayList<Sample>)bundle.getSerializable(ARG_SAMPLES);

        nameQuestion = (TextView)view.findViewById(R.id.name_question);
        recyclerView = (RecyclerView)view.findViewById(R.id.free_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        nameQuestion.setText(question.getName());

        freeQuestAdapter = new FreeQuestAdapter(samples,typeFree);
        recyclerView.setAdapter(freeQuestAdapter);


        return view;
    }
}
