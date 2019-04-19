package com.example.thimoteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "thimote.db";
    public static final String TABLE_NAME = "thimote";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Firstname";
    public static final String COL_3 = "Lastname";
    public static final String COL_4= "Username";
    public static final String COL_5 = "password";
    Cursor c;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE  TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY  AUTOINCREMENT , Firstname TEXT , Lastname TEXT, Username TEXT , Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
 onCreate(db);
    }
    public void insertdata(String ft_name ,String lt_name , String user_name , String pass_word ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,ft_name);
        contentValues.put(DatabaseHelper.COL_3,lt_name);
        contentValues.put(DatabaseHelper.COL_4,user_name);
        contentValues.put(DatabaseHelper.COL_5,pass_word);
        db.insert(DatabaseHelper.TABLE_NAME , null ,contentValues);

    }
}
