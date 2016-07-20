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
import com.sequenia.sibgurmanquestionnaire.activitis.MainActivity;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
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
    private ArrayList<AnswerdTypeSelect>answerdTypeSelects;

    private static final String ARG_SAMPLE_ANSWER = "com.sequenia.sibgurmanquestionnaire.fragments.sample_answer";
    private static final String ARG_SAMPLE_ANSWER_TYPE = "com.sequenia.sibgurmanquestionnaire.fragments.sample_answer_type";
    private static final String ARG_QUESIOTN = "com.sequenia.sibgurmanquestionnaire.fragments.question";
    private static final String ARG_INTERVIEW = "com.sequenia.sibgurmanquestionnaire.fragments.interview";
    public static SelectedFragment newInstance(Question question, ArrayList<Sample> sampleAns, ArrayList<AnswerdTypeSelect>answerdTypeSelects,int interview){
        SelectedFragment selectedFragment= new SelectedFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_QUESIOTN,(Serializable)question);
        bundle.putSerializable(ARG_SAMPLE_ANSWER,(Serializable)sampleAns);
        bundle.putSerializable(ARG_SAMPLE_ANSWER_TYPE,(Serializable)answerdTypeSelects);
        bundle.putInt(ARG_INTERVIEW,interview);
        selectedFragment.setArguments(bundle);
        return selectedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selected_fragment,container,false);

        Bundle bundle = getArguments();
        Question question = (Question)bundle.getSerializable(ARG_QUESIOTN);
        ArrayList<Sample>samples = new ArrayList<>(DatabeseHelpers.getSample(getActivity()));
        int invreview = bundle.getInt(ARG_INTERVIEW);
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

        answerdTypeSelects=new ArrayList<>(DatabeseHelpers.getAnswerdTypeSelect(getActivity(),question.getId(),invreview));
        for (AnswerdTypeSelect answerdTypeSelect:answerdTypeSelects){
            if(answerdTypeSelect.isAnswered()){
                ((RadioButton)radioGroup.getChildAt(answerdTypeSelect.getSimple_id()-1)).setChecked(true);
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ((RadioButton)radioGroup.findViewById(i)).setChecked(true);
                ArrayList<AnswerdTypeSelect>answerdTypeSelectsResult=new ArrayList<>();


                    for (int j=0;  j<radioGroup.getChildCount(); j++){
                        if (((RadioButton)radioGroup.getChildAt(j)).isChecked()){
                                 AnswerdTypeSelect answerdTypeSelect=answerdTypeSelects.get(j);
                                AnswerdTypeSelect answerdTypeSelectResult=new AnswerdTypeSelect();
                                answerdTypeSelectResult.setId(answerdTypeSelect.getId());
                                answerdTypeSelectResult.setAnsw_id(answerdTypeSelect.getAnsw_id());
                                answerdTypeSelectResult.setInterview_id(answerdTypeSelect.getInterview_id());
                                answerdTypeSelectResult.setSimple_id(answerdTypeSelect.getSimple_id());
                                answerdTypeSelectResult.setQuest_id(answerdTypeSelect.getQuest_id());
                                answerdTypeSelectResult.setAnswered(true);
                            answerdTypeSelectResult.setIsAnswerd(true);
                                DatabeseHelpers.updateAnswerdTypeSelected(getActivity(),answerdTypeSelectResult);

                        }else {
                                AnswerdTypeSelect answerdTypeSelect=answerdTypeSelects.get(j);
                                AnswerdTypeSelect answerdTypeSelectResult=new AnswerdTypeSelect();
                                answerdTypeSelectResult.setId(answerdTypeSelect.getId());
                                answerdTypeSelectResult.setAnsw_id(answerdTypeSelect.getAnsw_id());
                                answerdTypeSelectResult.setInterview_id(answerdTypeSelect.getInterview_id());
                                answerdTypeSelectResult.setSimple_id(answerdTypeSelect.getSimple_id());
                                answerdTypeSelectResult.setQuest_id(answerdTypeSelect.getQuest_id());
                                answerdTypeSelectResult.setAnswered(false);
                                answerdTypeSelectResult.setIsAnswerd(true);
                                DatabeseHelpers.updateAnswerdTypeSelected(getActivity(),answerdTypeSelectResult);
                            //}
                        }
                    }
                ((MainActivity)getActivity()).updateQuestion();
            }
        });

        return view;
    }
}
