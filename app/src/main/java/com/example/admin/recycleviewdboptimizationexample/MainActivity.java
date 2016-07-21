package com.example.admin.recycleviewdboptimizationexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());

        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
        HandsomeListAdapter adapter = new HandsomeListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //new DBAccessAsyncTask().execute();
        recyclerView.setAdapter(adapter);

/*
        for(int i=0; i<10000000; i++){
            addGenericHandsome();
        }
        */

    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cursor != null){
            cursor.close();
        }
        if(db != null){
            db.close();
        }
    }


    private class DBAccessAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                db = dbHelper.getReadableDatabase();
                cursor = db.query(DBHelper.HANDSOME_TABLE_NAME, null, null, null, null, null, null);
            }catch (SQLiteException e){
                Toast.makeText(MainActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();
                if(cursor != null){
                    cursor.close();
                }
                if(db != null){
                    db.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            HandsomeAdapter adapter = new HandsomeAdapter(cursor, MainActivity.this.getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
    }
*/
}
