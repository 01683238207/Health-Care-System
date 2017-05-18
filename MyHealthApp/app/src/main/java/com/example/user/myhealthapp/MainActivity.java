package com.example.user.myhealthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentOne.OnClickInterface{

    int i=1;
    double weight=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentOne fragmentOne=new FragmentOne();
        fragmentOne.setInterface(this);
        fragmentOne.setPositionAndSubPosition(i++,1);
        fragmentTransaction.add(R.id.frame,fragmentOne);
        fragmentTransaction.commit();


    }

    @Override
    public void clicked(float weight) {
        this.weight+=weight;
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentOne fragmentOne=new FragmentOne();
        fragmentOne.setInterface(this);
        fragmentOne.setPositionAndSubPosition(i++,1);
        fragmentTransaction.replace(R.id.frame,fragmentOne);
        fragmentTransaction.commit();
}

    @Override
    public void doneOperation() {
        SharedPreferences sp=getSharedPreferences("measurement",MODE_PRIVATE);
        sp.edit().putString("mvalue",""+weight).apply();
        Toast.makeText(this, "Total weight is"+weight, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,finalActivity.class);
        intent.putExtra("measure",weight);
        startActivity(intent);
        finish();
    }

}
