package com.example.user.myhealthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    double result=0;
    ArrayList<QuestionaryDetail> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView= (ListView) findViewById(R.id.list);
        button= (Button) findViewById(R.id.submit);

        getSupportActionBar().setTitle("Questionnaire Section");

        list=new QuestionnarieDatabase(this).show();
        Adapter a=new Adapter(this,list);

        listView.setAdapter(a);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(QuestionaryDetail qd:list){
                    if(qd.getResponce()==-1){
                        Toast.makeText(SecondActivity.this, "All the questions must be answered", Toast.LENGTH_SHORT).show();
                        result=0;
                        return;
                    }
                    else {
                        result+=qd.getWeight()*qd.getResponce();
                    }
                }
                SharedPreferences sp=getSharedPreferences("questionary",MODE_PRIVATE);
                sp.edit().putString("value",""+result).apply();
                Intent intent=new Intent(SecondActivity.this,MeasurmentActivity.class);
                intent.putExtra("imam",result);
                startActivity(intent);
                finish();
            }
        });
    }

    }
