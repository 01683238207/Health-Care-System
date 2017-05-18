package com.example.user.myhealthapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TotalActivity extends AppCompatActivity implements View.OnClickListener {
    double totalResult;
    TextView textView1,textView2;
    Button exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        totalResult=Double.parseDouble(getSharedPreferences("measurement", MODE_PRIVATE).getString("mvalue","1"))+Double.parseDouble(getSharedPreferences("questionary", MODE_PRIVATE).getString("value","1"));
        textView1=(TextView) findViewById(R.id.totalView1);
        textView2=(TextView) findViewById(R.id.totalView2);
        exitButton=(Button) findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(this);
        textView1.setText("Your measurement stress score is: "+totalResult);
        if(totalResult>=1 && totalResult<=12){
            textView2.setText("Congratulations! You are free from stress-related illness.");
        }
        else if(totalResult>12 && totalResult<=20){
            textView2.setText("You are more likely to experience stress related ill health either mental, physical or both. You can take advice or consult with a psychologist.");
        }
        else if(totalResult>20 && totalResult<=30){
            textView2.setText("You are the most prone to stress showing a great many traits or " +
                    "characteristics that are creating un-healthy behaviors and  stress-related illness e.g. diabetes, depression, anxiety, migraine, back and neck pain, high blood pressure, heart disease, strokes, mental ill health. " +
                    "Very quickly Consult your medical practitioner.");

        }
        else if(totalResult>30){
            textView2.setText("You bear highest risky stress, You need to emergency call your doctor.");

        }

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
