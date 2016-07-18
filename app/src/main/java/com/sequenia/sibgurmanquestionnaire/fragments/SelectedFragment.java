package com.sequenia.sibgurmanquestionnaire.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeSelect;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ivan1 on 07.07.2016.
 */
public class SelectedFragment extends Fragment{

    private TextView nameQuestion;
    RadioGroup radioGroup;

    private static final String ARG_SELECT="com.sequenia.sibgurmanquestionnaire.fragments.select";
    private static final String ARG_SAMPLES="com.sequenia.sibgurmanquestionnaire.fragments.samples";
    private static final String ARG_TYPE_QUEST="com.sequenia.sibgurmanquestionnaire.fragments.type_quest";

    public static SelectedFragment newInstance(Question question, ArrayList<Sample> samples){
        SelectedFragment selectedFragment= new SelectedFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_SELECT,(Serializable)question);
        bundle.putSerializable(ARG_SAMPLES,samples);
        selectedFragment.setArguments(bundle);
        return selectedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_fragment,container,false);

        Bundle bundle = getArguments();
        Question question = (Question)bundle.getSerializable(ARG_SELECT);
        ArrayList<Sample>samples = (ArrayList<Sample>)bundle.get(ARG_SAMPLES);

        nameQuestion = (TextView)view.findViewById(R.id.quest_name);
        radioGroup = (RadioGroup)view.findViewById(R.id.select_semple);
        nameQuestion.setText(question.getName());
        RadioButton radioButton;
        for (Sample sample:samples){
            radioButton = new RadioButton(getActivity());
            radioButton.setId(sample.getId());
            radioButton.setText(sample.getName());
            radioGroup.addView(radioButton);
        }

        return view;
    }
}
