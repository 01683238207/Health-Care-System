package com.example.user.myhealthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finalActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView1,textView2,textView3;
    Button totalMeasure;
    double result;
    double weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        result=getIntent().getDoubleExtra("measure",1);
        textView1=(TextView)findViewById(R.id.viewMeasure1);
        textView2=(TextView)findViewById(R.id.viewMeasure2);
        totalMeasure=(Button) findViewById(R.id.btnTotal);
        totalMeasure.setOnClickListener(this);
        textView1.setText("Your measurement stress score is: "+result);
        if(result>=1 && result<=5){
            textView2.setText("Congratulations! You are free from stress-related illness.");
        }
        else if(result>5 && result<=10){
            textView2.setText("You are more likely to experience stress related ill health either mental, physical or both. You can take advice or consult with a psychologist.");
        }
        else if(result>10){
            textView2.setText("You are the most prone to stress showing a great many traits or " +
                    "characteristics that are creating un-healthy behaviors and  stress-related illness e.g. diabetes, depression, anxiety, migraine, back and neck pain, high blood pressure, heart disease, strokes, mental ill health. " +
                    "Very quickly Consult your medical practitioner or emergency call your doctor.");

        }
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(finalActivity.this,TotalActivity.class);
        intent.putExtra("measure",weight);
        startActivity(intent);
        finish();
    }
}
