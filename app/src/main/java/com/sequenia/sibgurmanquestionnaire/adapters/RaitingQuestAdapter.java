package com.sequenia.sibgurmanquestionnaire.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.activitis.MainActivity;
import com.sequenia.sibgurmanquestionnaire.helpers.DatabeseHelpers;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;

import java.util.ArrayList;

/**
 * Created by ivan1 on 11.07.2016.
 */
public class RaitingQuestAdapter extends RecyclerView.Adapter<RaitingQuestAdapter.ViewHolder> {


    private ArrayList<Sample>samples;
    int interview;
    private ArrayList<AnswerdTypeRaing> answerdTypeRaings;
    private TypeRaing typeRaing;
    private Question question;
    private Context context;
    private int position;

    public RaitingQuestAdapter(Context context, Question question, ArrayList<Sample> samples, TypeRaing typeRaing, int invreview){
        this.samples=samples;
        this.typeRaing=typeRaing;
        this.context=context;
        this.interview=invreview;
        this.question = question;
        this.answerdTypeRaings=new ArrayList<>(DatabeseHelpers.getAnsweredTypeRaing(context,question.getId(),invreview));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating,parent,false);
       // ViewHolder vh = new ViewHolder(v);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

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
                holder.resultRaiting.setText(seekBar.getProgress()+1+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                holder.resultRaiting.setText(seekBar.getProgress()+1+"");
                AnswerdTypeRaing answerdTypeRaingResult=new AnswerdTypeRaing();

                for (AnswerdTypeRaing answerdTypeRaing:answerdTypeRaings){
                    if (answerdTypeRaing.getSimple_id()==position+1){
                        answerdTypeRaingResult.setAnsw_id(answerdTypeRaing.getAnsw_id());
                        answerdTypeRaingResult.setId(answerdTypeRaing.getId());
                        answerdTypeRaingResult.setInterview_id(answerdTypeRaing.getInterview_id());
                        answerdTypeRaingResult.setAnswered(seekBar.getProgress());
                        answerdTypeRaingResult.setQuest_id(answerdTypeRaing.getQuest_id());
                        answerdTypeRaingResult.setSimple_id(answerdTypeRaing.getSimple_id());
                        answerdTypeRaingResult.setIsAnswerd(true);

                        DatabeseHelpers.updateAnswerdTypeRaing(context,answerdTypeRaingResult);
                        ((MainActivity)context).updateQuestion();

                    }
                }


            }
        });
        for (AnswerdTypeRaing answerdTypeRaing:answerdTypeRaings){
            if (answerdTypeRaing.getSimple_id()==position+1){
                holder.resultRaiting.setText(answerdTypeRaing.getAnswered()+"");
                int answer=answerdTypeRaing.getAnswered();
                holder.seekBar.setProgress(answer);
            }
        }


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
