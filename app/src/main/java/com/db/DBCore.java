package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCore extends SQLiteOpenHelper {
    private static final String BD_NAME = "saveapp";
    private static final int BD_VERSION = 34;

    public DBCore(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists accounts(_id integer primary key autoincrement, name text not null, isNew integer not null)");
        db.execSQL("create table if not exists operations(_id integer primary key autoincrement, name text not null, type text not null, value real not null, id_accounts integer not null, foreign key(id_accounts) references accounts(_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists accounts;");
        db.execSQL("drop table if exists operations;");
        onCreate(db);
    }
}
