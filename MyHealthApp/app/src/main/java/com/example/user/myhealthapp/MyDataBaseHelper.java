package com.example.user.myhealthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 3/5/2017.
 */

class MyDataBaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "HEALTH";
    static final int VERSION =1;
    static final String TABLE_NAME = "health_table";
    String quary = "Create Table " + TABLE_NAME + "" +
            "(" +
            "_id INTEGER PRIMARY KEY autoincrement," +
            "_question text," +
            "_subquestion1 text," +
            "_hint text," +
            "_provided_answer text" +
            ")";


    public MyDataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(quary);
        sqLiteDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_subquestion1,_hint) values (' Have you headache?','Time duration of headache','day of headach')");
        sqLiteDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_subquestion1,_hint) values (' Have you blood presser?','Range of blood presser','systolic-diastolic')");
        sqLiteDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_subquestion1,_hint) values (' Are you diabetic?','Blood sugar range','before-after(mg/dL)')");
        sqLiteDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_subquestion1,_hint) values (' Have you Skin Temp. problem?','What that temperature?','Fahrenheit Scale')");
        sqLiteDatabase.execSQL("Insert into " + TABLE_NAME + " (_question,_subquestion1,_hint) values (' Have you Heart Rate problem?','Pulse Rate','Give pulse rate (bpm)')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME + ";");
        onCreate(sqLiteDatabase);
    }

    long insertData(String st, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_provided_answer", st);
        long inserted = db.update(TABLE_NAME, values, "_id=?", new String[]{"" + i});
        db.close();
        return inserted;
    }

    int getCountOfRow(){
        Cursor c=getReadableDatabase().query(MyDataBaseHelper.TABLE_NAME,null,null,null,null,null,null);
        int x=c.getCount();
        c.close();
        return  x;
    }

}
