package com.example.admin.recycleviewdboptimizationexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.URL;

/**
 * Created by admin on 7/19/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Handsome_DB";
    public static final String NAME_KEY = "NAME";
    public static final String URL_KEY = "IMAGE_URL";
    public static final String HANDSOME_TABLE_NAME = "HANDSOME";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder query = new StringBuilder(100);
        query.append("CREATE TABLE ").append(HANDSOME_TABLE_NAME).append("( ")
                .append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(NAME_KEY).append(" TEXT, ")
                .append(URL_KEY).append(" TEXT );");
        sqLiteDatabase.execSQL(query.toString()
        );

        insertHandsome(sqLiteDatabase, "John Nuccio", "https://dl.dropboxusercontent.com/u/103474456/john.jpg");
        insertHandsome(sqLiteDatabase, "Jonathan Sanchez", "https://dl.dropboxusercontent.com/u/103474456/jonathan.jpg");
        insertHandsome(sqLiteDatabase, "Andy Lin", "https://dl.dropboxusercontent.com/u/103474456/Andy.png");
        insertHandsome(sqLiteDatabase, "Eugenio Kuri", "https://dl.dropboxusercontent.com/u/103474456/kuri.png");
/*
        for(int i=0; i<100000; i++){
            insertHandsome(sqLiteDatabase, "Handsome", "http://1000funnypictures.com/wp-content/uploads/Ugly-Women-10.jpg");
        }
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void insertHandsome(SQLiteDatabase db, String name, String url){
        ContentValues values = new ContentValues();
        values.put(NAME_KEY, name);
        values.put(URL_KEY, url);
        db.insert(HANDSOME_TABLE_NAME, null, values);
    }

}
