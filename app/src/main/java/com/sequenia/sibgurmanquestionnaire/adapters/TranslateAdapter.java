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
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ivan1 on 12.07.2016.
 */
public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {

    ArrayList<Sample> samples;
    TypeTranslate typeTranslate;
    Context context;

    public TranslateAdapter(Context context,ArrayList<Sample>samples, TypeTranslate typeTranslate){
        this.samples=samples;
        this.typeTranslate=typeTranslate;
        this.context=context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transcript,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameSample.setText(samples.get(position).getName());
        holder.bind(typeTranslate,context);

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

        public void bind(TypeTranslate typeTranslate, Context context){
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
