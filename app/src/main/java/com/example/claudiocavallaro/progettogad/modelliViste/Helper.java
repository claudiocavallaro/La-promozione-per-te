package com.example.claudiocavallaro.progettogad.modelliViste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by claudiocavallaro on 31/01/16.
 */
public class Helper extends SQLiteOpenHelper {

    private static final String nomeDB = "bb.db";
    private static final int DB_VERSION = 1;

    private static final String createDB = "create table fav(name text not null)";

    public Helper(Context context){
        super(context, nomeDB, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fav");
        onCreate(db);
    }
}
