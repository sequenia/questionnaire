package com.sequenia.sibgurmanquestionnaire.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.activitis.MainActivity;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ivan1 on 12.07.2016.
 */
public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {

    ArrayList<Sample> samples;
    TypeTranslate typeTranslate;
    ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    Context context;

    public TranslateAdapter(Context context, Question question, ArrayList<Sample> samples, TypeTranslate typeTranslate, int invreview){
        this.samples=samples;
        this.typeTranslate=typeTranslate;
        this.context=context;

        this.answerdTypeTranslates=new ArrayList<>(DatabeseHelpers.getAnswerdTypeTranslate(context,question.getId(),invreview));


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transcript,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameSample.setText(samples.get(position).getName());
        holder.bind(typeTranslate,context);

        for (AnswerdTypeTranslate answerdTypeTranslate:answerdTypeTranslates){
            if (answerdTypeTranslate.getSimple_id()==position+1){

                for (int i=0;i<holder.radioGroup.getChildCount();i++) {
                    if (i==answerdTypeTranslate.getAnswered()) {
                        ((RadioButton) holder.radioGroup.getChildAt(i)).setChecked(true);
                    }
                }
            }
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

               ((RadioButton)radioGroup.findViewById(i)).setChecked(true);
                AnswerdTypeTranslate answerdTypeTranslateResult = new AnswerdTypeTranslate();
                for (AnswerdTypeTranslate answerdTypeTranslate:answerdTypeTranslates){
                    if (answerdTypeTranslate.getSimple_id()==position+1){
                        for (int j=0;j<radioGroup.getChildCount();j++) {

                            if(((RadioButton) radioGroup.getChildAt(j)).isChecked())
                            {
                                answerdTypeTranslateResult.setAnsw_id(answerdTypeTranslate.getAnsw_id());
                                answerdTypeTranslateResult.setId(answerdTypeTranslate.getId());
                                answerdTypeTranslateResult.setInterview_id(answerdTypeTranslate.getInterview_id());
                                answerdTypeTranslateResult.setAnswered(j);
                                answerdTypeTranslateResult.setAnserTrans(((RadioButton) radioGroup.getChildAt(j)).getText().toString());
                                answerdTypeTranslateResult.setQuest_id(answerdTypeTranslate.getQuest_id());
                                answerdTypeTranslateResult.setSimple_id(answerdTypeTranslate.getSimple_id());
                                answerdTypeTranslateResult.setIsAnswerd(true);

                                DatabeseHelpers.updateAnswerdTypeTranslate(context,answerdTypeTranslateResult);
                                ((MainActivity)context).updateQuestion();
                            }

                        }
                    }
                }



                }

        });
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameSample;
        private RadioGroup radioGroup;
        public ViewHolder(View itemView) {
            super(itemView);
            nameSample=(TextView)itemView.findViewById(R.id.name_sample);
            radioGroup = (RadioGroup)itemView.findViewById(R.id.variant_quest);

        }

        public void bind(TypeTranslate typeTranslate, Context context ){
            HashMap<Integer,String> variants=typeTranslate.getVariants();
            String str;
            RadioButton radioButton;
            for (int i=1; i<=variants.size();i++){
                str=variants.get((Integer)i);
                radioButton = new RadioButton(context);
                radioButton.setText(str);
                radioGroup.addView(radioButton);
            }


        }
    }
}
