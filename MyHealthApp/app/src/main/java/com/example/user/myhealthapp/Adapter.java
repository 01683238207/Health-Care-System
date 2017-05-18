package com.example.user.myhealthapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 5/8/2017.
 */

public class Adapter extends ArrayAdapter<QuestionaryDetail> {
    ArrayList<QuestionaryDetail> objects;
    public Adapter(Context context ,ArrayList<QuestionaryDetail> objects) {
        super(context,0);
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;
        v=inflater.inflate(R.layout.row_design,parent,false);
        TextView qusetion;
        RadioGroup group;
        RadioButton but1,but2;
        final int pos=position;
        qusetion= (TextView) v.findViewById(R.id.qus);
        group= (RadioGroup) v.findViewById(R.id.group);
        but1= (RadioButton) v.findViewById(R.id.yes);
        but2= (RadioButton) v.findViewById(R.id.no);
        qusetion.setText(getItem(position).getQuestion());
        switch (getItem(position).getResponce()){
            case 1:
                but1.setChecked(true);
                break;
            case 0:
                but2.setChecked(true);
                break;
            default:
                but1.setChecked(false);
                but2.setChecked(false);
                break;
        }
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.yes:
                        objects.get(pos).setResponce(1);
                        break;
                    case R.id.no:
                        objects.get(pos).setResponce(0);
                        break;
                }
            }
        });
        return v;
    }


    @Override
    public QuestionaryDetail getItem(int position) {
        return objects.get(position);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

}
