package com.sequenia.sibgurmanquestionnaire.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ivan1 on 12.07.2016.
 */
public class FreeQuestAdapter extends RecyclerView.Adapter<FreeQuestAdapter.ViewHolder> {
    ArrayList<Sample> samples;
    TypeFree typeFree;

    public FreeQuestAdapter(ArrayList<Sample>samples, TypeFree typeFree){
        this.samples=samples;
        this.typeFree=typeFree;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameSample.setText(samples.get(position).getName());
        holder.positiveLbl.setText(typeFree.getPositive());
        holder.negativeLbl.setText(typeFree.getNegative());
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
