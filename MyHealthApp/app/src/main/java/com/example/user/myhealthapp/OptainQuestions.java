package com.example.user.myhealthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.StateSet;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 3/5/2017.
 */

class OptainQuestions {
    private static ArrayList<String> questions=new ArrayList<>();
    private static ArrayList<ArrayList<String>> subQuestions=new ArrayList<>();
    private static ArrayList<String> hints=new ArrayList<>();
    public OptainQuestions(Context context) {
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(context);
        SQLiteDatabase sqLiteDatabase=myDataBaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select _question,_subquestion1,_hint from "+MyDataBaseHelper.TABLE_NAME+";",null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do {
                    ArrayList<String> subqus=new ArrayList<>();
                    String question=cursor.getString(0);
                    String subquestion1=cursor.getString(1)==null?"":cursor.getString(1);
                    String hint=cursor.getString(2)==null?"":cursor.getString(2);
                    if(!subquestion1.isEmpty())
                        subqus.add(subquestion1);
                    if(!hint.isEmpty())
                        hints.add(hint);
                    Log.w("imam",question);
                    Log.w("imam",subqus.toString());
                    questions.add(question);
                    subQuestions.add(subqus);
                }while (cursor.moveToNext());
            }
        }else Log.w("Imam","Cursor is null");

        assert cursor != null;
        cursor.close();
        sqLiteDatabase.close();
    }

    public static String getQuestion(int pos){
        return questions.get(pos-1);
    }

    public static String subgetQuestion(int pos,int subQusNo){
        ArrayList<String> subQus=subQuestions.get(pos-1);
        return subQus.get(subQusNo-1);
    }

    public static String getHints(int pos){
        return hints.get(pos-1);
    }

    static int getTotalQuestion(){
        return questions.size();
    }
}
