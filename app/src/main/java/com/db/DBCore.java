package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Daniel on 01/05/2015.
 */
public class DBCore extends SQLiteOpenHelper {
    private static final String BD_NAME = "contas"; // constante criada para atribuir nome ao bd
    private static final int BD_VERSION = 6; // constante criada para atribuir uma versão do bd

    public DBCore(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION); // esse construtor da classe chama a superclasse SQLiteOpenHelper, passando o ctx, nome do bd, um cursor que não vamos utilizar e a versão atual do banco para criá-lo.



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists accounts(_id integer primary key autoincrement, name text not null)");
        db.execSQL("create table if not exists operations(_id integer primary key autoincrement, name text not null, type text not null, value real not null, id_accounts integer not null, foreign key(id_accounts) references accounts(_id))");
        // tabela contas: id auto increment e chave primaria, nome
       // tabela operacoes: id auto increment, chave primaria, nome, tipo, valor, conta_id(chave estrangeira)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists accounts;");
        db.execSQL("drop table if exists operations;");
        onCreate(db);

        // destroi o banco e chama onCreate para refazê-lo
    }
}
