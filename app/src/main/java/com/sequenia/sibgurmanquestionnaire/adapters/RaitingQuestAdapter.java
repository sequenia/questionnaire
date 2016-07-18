package com.sequenia.sibgurmanquestionnaire.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;

import java.util.ArrayList;

/**
 * Created by ivan1 on 11.07.2016.
 */
public class RaitingQuestAdapter extends RecyclerView.Adapter<RaitingQuestAdapter.ViewHolder> {


    private ArrayList<Sample>samples;
    private TypeRaing typeRaing;

    public RaitingQuestAdapter(ArrayList<Sample> samples, TypeRaing typeRaing){
        this.samples=samples;
        this.typeRaing=typeRaing;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int average = (int)typeRaing.getMax()/2;
        holder.textView.setText(samples.get(position).getName());
        holder.seekBar.setMax(typeRaing.getMax()-1);
        holder.seekBar.setProgress(average);
        holder.minLabel.setText(typeRaing.getMinTranscript());
        holder.maxLabel.setText(typeRaing.getMaxTranscript());

        holder.resultRaiting.setText(average+1+"");
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                holder.resultRaiting.setText(i+1+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        SeekBar seekBar;
        TextView minLabel;
        TextView maxLabel;
        TextView resultRaiting;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name_sample);
            seekBar = (SeekBar)itemView.findViewById(R.id.seekBar);
            minLabel = (TextView)itemView.findViewById(R.id.minLabel);
            maxLabel=(TextView) itemView.findViewById(R.id.maxLabel);
            resultRaiting = (TextView)itemView.findViewById(R.id.result_raiting);

        }
    }
}
