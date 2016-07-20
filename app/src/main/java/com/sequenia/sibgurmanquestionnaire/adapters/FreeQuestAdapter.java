package com.sequenia.sibgurmanquestionnaire.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.activitis.MainActivity;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ivan1 on 12.07.2016.
 */
public class FreeQuestAdapter extends RecyclerView.Adapter<FreeQuestAdapter.ViewHolder> {
    ArrayList<Sample> samples;

    private ArrayList<AnswerdTypeFree> answerdTypeFrees;
    TypeFree typeFree;
    Context context;

    public FreeQuestAdapter(Context context, Question question, ArrayList<Sample> samples,TypeFree typeFree, int invreview){
        this.samples=samples;
        this.context=context;
        this.typeFree=typeFree;
        this.answerdTypeFrees=new ArrayList<>(DatabeseHelpers.getAnsweredTypeFree(context,question.getId(),invreview));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameSample.setText(samples.get(position).getName());
        holder.positiveLbl.setText(typeFree.getPositive());
        holder.negativeLbl.setText(typeFree.getNegative());


        for (AnswerdTypeFree answerdTypeFree:answerdTypeFrees){
            if (answerdTypeFree.getSample_id()==position+1){
                holder.positive.setText(answerdTypeFree.getPositive());
                holder.negative.setText(answerdTypeFree.getNegative());
            }
        }

        holder.negative.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AnswerdTypeFree answerdTypeFreeResult=new AnswerdTypeFree();
                for (AnswerdTypeFree answerdTypeFree:answerdTypeFrees){
                    if (answerdTypeFree.getSample_id()==position+1){
                        answerdTypeFreeResult.setAnsw_id(answerdTypeFree.getAnsw_id());
                        answerdTypeFreeResult.setId(answerdTypeFree.getId());
                        answerdTypeFreeResult.setSample_id(answerdTypeFree.getSample_id());
                        answerdTypeFreeResult.setInterview_id(answerdTypeFree.getInterview_id());
                        answerdTypeFreeResult.setNegative(charSequence.toString());
                        answerdTypeFreeResult.setPositive(answerdTypeFree.getPositive());
                        answerdTypeFreeResult.setIsAnswerd(true);

                        DatabeseHelpers.updateAnswerTypeFree(context,answerdTypeFreeResult);
                        ((MainActivity)context).updateQuestion();

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        holder.positive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AnswerdTypeFree answerdTypeFreeResult=new AnswerdTypeFree();
                for (AnswerdTypeFree answerdTypeFree:answerdTypeFrees){
                    if (answerdTypeFree.getSample_id()==position+1){
                        answerdTypeFreeResult.setAnsw_id(answerdTypeFree.getAnsw_id());
                        answerdTypeFreeResult.setId(answerdTypeFree.getId());
                        answerdTypeFreeResult.setSample_id(answerdTypeFree.getSample_id());
                        answerdTypeFreeResult.setInterview_id(answerdTypeFree.getInterview_id());
                        answerdTypeFreeResult.setNegative(answerdTypeFree.getNegative());
                        answerdTypeFreeResult.setPositive(charSequence.toString());
                        answerdTypeFreeResult.setIsAnswerd(true);

                        DatabeseHelpers.updateAnswerTypeFree(context,answerdTypeFreeResult);
                        ((MainActivity)context).updateQuestion();

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return samples.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameSample;
        private TextView positiveLbl;
        private TextView negativeLbl;
        private EditText positive;
        private EditText negative;

        public ViewHolder(View itemView) {
            super(itemView);
            nameSample = (TextView)itemView.findViewById(R.id.name_sample);
            positiveLbl = (TextView)itemView.findViewById(R.id.positive_lbl);
            negativeLbl = (TextView)itemView.findViewById(R.id.negaitive_lbl);
            positive = (EditText)itemView.findViewById(R.id.positive);
            negative = (EditText)itemView.findViewById(R.id.negative);
        }
    }
}
