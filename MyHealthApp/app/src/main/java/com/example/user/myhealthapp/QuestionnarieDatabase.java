package com.example.user.myhealthapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 5/8/2017.
 */

public class QuestionnarieDatabase extends SQLiteOpenHelper {
    static final String DB_NAME = "Questionnaire";
    static final int VERSION =1;
    static final String TABLE_NAME = "questionnaire_table";
    String quary = "Create Table " + TABLE_NAME + "" +
            "(" +
            "_id INTEGER PRIMARY KEY autoincrement," +
            "_question text," +
            "_response integer," +
            "_weight float," +
            "_result float" +
            ")";

    public QuestionnarieDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase questionnaireDatabase) {
        questionnaireDatabase.execSQL(quary);
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values ('I Frequently Bring Work Home At Night ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Deny or Ignore Problem When I Face ',1.5)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' My Self Confidence is Lower than I would Like It To Be ',1.75)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Feel Life Is Difficult For Me ',2)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Feel Guilty If I Relax or Do Nothing ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Feel Tired When I Wake After Adequate Sleep ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Have Tendency To Eat,Walk and Drive Quickly ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' If Someone Annoy me I Will Bottle up my Feelings ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Find Fault And Criticize Others Rather Than Praising ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Find Myself Grinding My Teeth ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' Increase in Muscular Aches And Pains in Neck,Head,Low Back,Shoulders ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I am Unable To Perform My Task ',1.2)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' My Judgment is Clouded or Not As Good  ',1.5)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Find I Have Greater Dependency on Alcohol,Nicotine or Drug  ',1.75)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Find I Have No Time For Interest Or Hobbies Outside the Work  ',1.5)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' I Seem To be Listening Even Though I am Preoccupied My Own Thought ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' When I Suddenly Hear Bad News then I Feel Extrim Nervous ',1.5)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' Sleep 5 Hours at Night ',1.5)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' See, Hear, and Smell Things That Others Do Not  ',1)");
        questionnaireDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_weight) values (' Forget to Eat and Feel Little Hunger  ',1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase questionnaireDatabase, int oldVersion, int newVersion) {
        questionnaireDatabase.execSQL("Drop table if exists " + TABLE_NAME + ";");
        onCreate(questionnaireDatabase);

    }

    public ArrayList<QuestionaryDetail> show() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<QuestionaryDetail> list=new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("Select _question,_weight from " + TABLE_NAME + ";", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String question = cursor.getString(0);
                    float weight = cursor.getFloat(1);
                    QuestionaryDetail detail=new QuestionaryDetail(question,weight);
                    list.add(detail);
                } while (cursor.moveToNext());
            }
        }

        assert cursor != null;
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
}
