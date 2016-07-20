package com.sequenia.sibgurmanquestionnaire.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.models.Question;

import java.util.ArrayList;

/**
 * Created by ivan1 on 06.07.2016.
 */
public class QestionAdapter extends BaseAdapter {

    Context context;
    LayoutInflater lInflater;

    ArrayList<Question> objects;

    public QestionAdapter(Context context,LayoutInflater inflater,ArrayList<Question>questions){
        this.context=context;
        this.lInflater=inflater;
        this.objects=questions;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1=view;
        if (view1==null){
            view1=lInflater.inflate(R.layout.item_question,viewGroup,false);
        }

        Question q = getQuestion(i);

        TextView qestiontext=(TextView)view1.findViewById(R.id.question);
        if (q.isAnswered()){
            qestiontext.setTextColor(context.getResources().getColor(R.color.green));
        }else
            if (q.isRequired()){
                qestiontext.setTextColor(context.getResources().getColor(R.color.red));
            }
            else {
                qestiontext.setTextColor(context.getResources().getColor(R.color.gray));
            }

        qestiontext.setText(q.getName());

        return view1;
    }

    Question getQuestion(int i){
        return ((Question)getItem(i));
    }

}
