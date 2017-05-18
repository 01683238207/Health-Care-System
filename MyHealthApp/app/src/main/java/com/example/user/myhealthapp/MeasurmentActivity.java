package com.example.user.myhealthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MeasurmentActivity extends AppCompatActivity implements View.OnClickListener{
TextView textView1,textView2,textView3;
    Button btnMeasur,btnExit;
    double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurment);
        result=getIntent().getDoubleExtra("imam",0);
        textView1=(TextView)findViewById(R.id.textmt1);
        textView2=(TextView)findViewById(R.id.textmt2);
        textView3=(TextView)findViewById(R.id.textmt3);
        btnMeasur=(Button) findViewById(R.id.btnMeasure);
        btnExit=(Button) findViewById(R.id.btnExit);
        btnMeasur.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        Toast.makeText(this, "Data Received", Toast.LENGTH_SHORT).show();
        textView1.setText("Your stress score is: "+result);
        if(result>=1 && result<=5){
            textView2.setText("Congratulations! You are free from stress-related illness.");
        }
        else if(result>5 && result<=15){
            textView2.setText("You are more likely to experience stress related ill health either mental, physical or both. You can take advice or consult with a psychologist.");
        }
        else if(result>15){
            textView2.setText("You are the most prone to stress showing a great many traits or " +
                    "characteristics that are creating un-healthy behaviors and stress-related illness" +
                    " e.g. diabetes, depression, anxiety, migraine, back and neck pain, high blood pressure, heart disease, strokes, mental ill health. " +
                    "Very quickly Consult your medical practitioner or emergency call your doctor.");

        }
        textView3.setText("Are You wish to go measurement section? Click Measurement Section Otherwise Click Exit");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMeasure:
                Intent intent=new Intent(MeasurmentActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
