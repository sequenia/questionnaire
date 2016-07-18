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
import com.sequenia.sibgurmanquestionnaire.adapters.QestionAdapter;
import com.sequenia.sibgurmanquestionnaire.adapters.RaitingQuestAdapter;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class TypeRatingFragment extends Fragment{

    private TextView nameQuestion;
    private TextView commentQuestion;
    private RecyclerView recyclerView;
    private RaitingQuestAdapter raitingQuestAdapter;


    private static final String ARG_RATING="com.sequenia.sibgurmanquestionnaire.fragments.rating";
    private static final String ARG_SAMPLES="com.sequenia.sibgurmanquestionnaire.fragments.samples";
    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";

    private Question question;
    public static TypeRatingFragment newInstance(Question question, ArrayList<Sample>samples,TypeRaing typeRaing){
        TypeRatingFragment fragment = new TypeRatingFragment();
        Bundle bundle= new Bundle();
        bundle.putSerializable(ARG_RATING, (Serializable) question);
        bundle.putSerializable(ARG_TYPE_QUEST,(Serializable)typeRaing);
        bundle.putSerializable(ARG_SAMPLES,samples);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.type_rating_fragmnet,container,false);

        Bundle bundle = getArguments();
        Question question =(Question) bundle.getSerializable(ARG_RATING);
        TypeRaing typeRaing= (TypeRaing)bundle.get(ARG_TYPE_QUEST);
        ArrayList<Sample> samples= (ArrayList<Sample>) bundle.getSerializable(ARG_SAMPLES);

        nameQuestion = (TextView)view.findViewById(R.id.name_question);
        commentQuestion = (TextView)view.findViewById(R.id.comment_question);
        recyclerView = (RecyclerView)view.findViewById(R.id.raiting_recycler);
        recyclerView.setHasFixedSize(false);

        nameQuestion.setText(question.getName());
        commentQuestion.setText(typeRaing.getCommit());

        raitingQuestAdapter = new RaitingQuestAdapter(samples,typeRaing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(raitingQuestAdapter);




        return view;
    }
}
