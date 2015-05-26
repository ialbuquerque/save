package com.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Daniel on 01/05/2015.
 */
public class DBCore extends SQLiteOpenHelper {
    private static final String BD_NAME = "contas"; // constante criada para atribuir nome ao bd
    private static final int BD_VERSION = 2; // constante criada para atribuir uma versão do bd

    public DBCore(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION); // esse construtor da classe chama a superclasse SQLiteOpenHelper, passando o ctx, nome do bd, um cursor que não vamos utilizar e a versão atual do banco para criá-lo.



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table accounts(_id integer primary key autoincrement, name text not null)");
       // db.execSQL("create table operations(_id integer primary key autoincrement, type text not null, value real not null, id_accounts integer not null, foreign key(id_accounts) references accounts(_id)");
        // sintaxe comum do sqlite para criação da tabela: id, nome, tipo do operador da conta, valor da operação
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table accounts;");
        //db.execSQL("drop table operations;");
        onCreate(db);

        // destroi o banco e chama onCreate para refazê-lo, futuramente colocaremos algo pra recuperar os dados
    }
}
