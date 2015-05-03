package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domain.Account;
import com.domain.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 01/05/2015.
 */
public class DB {
    private SQLiteDatabase db;

    public DB(Context context){
        DBCore auxdb = new DBCore(context);
        db= auxdb.getWritableDatabase();

    }

    public void push(Account account){
        ContentValues values = new ContentValues(); //cria um content para enviar a requisição
        values.put("name",account.getName());
        for (Operator o : account.getOperators()) {
            values.put("name",o.getName());
            values.put("value",o.getValue());
            values.put("type_operator",o.getType());
            //utilizei um for para recuperar as informações do atributo List<Operator> de Account
        }
        //metodo put sai colocando o que se deseja dentro do content, aceita uma string e diversos tipos como String, double, byte, int

        db.insert("accounts",null,values);
    }

    public void refresh(Account account){
        ContentValues values = new ContentValues(); //cria um content para enviar a requisição
        values.put("name",account.getName());
        for (Operator o : account.getOperators()) {
            values.put("name",o.getName());
            values.put("value",o.getValue());
            values.put("type_operator",o.getType());
            //utilizei um for para recuperar as informações do atributo List<Operator> de Account
        }
        //metodo put sai colocando o que se deseja dentro do content, aceita uma string e diversos tipos como String, double, byte, int

        db.update("accounts",values,"name = ?",  new String[]{""+account.getName()});
        //o terceiro parâmetro permite que você defina as where clauses, e se colocar "?" faz com que isso seja preenchido com o conteúdo do último parâmetro
        //O último parametro permite que se coloque where clauses externas, aqui fiz com uma string mesmo, mas poderia vir de outro lugar

    }

    public void drop(Account account){
        db.delete("accounts"," name = "+account.getName(),null);
        //aqui ja fiz a where clause dentro do parametro, e o externo passei como null


    }

    public List<Account> search() {
        List<Account> list = new ArrayList<Account>();

        String[] columns = new String[]{ "name", "type_operator", "value"};

        Cursor cursor = db.query("accounts", columns, null, null, null, null, "type_operator ASC");//parametros null indicam clausulas diversas, escolhi apenas order by

        if (cursor.getCount() > 0) { //checa se o cursor encontrou resultados na busca e prossegue
            cursor.moveToFirst();

            do {

                Account a = new Account();
                a.setName(cursor.getString(0));
                /**for (Operator o : a.getOperators()) {
                    a.setOperators(o.setName(cursor.getString(1));
                    a.setOperators(o.setValue(cursor.getDouble(2));
                } Aparentemente esse for está correto em sintaxe e em lógica, mas ele está dando erro por algum motivo, por isso deixo aqui comentado.*/
                list.add(a);
          }while (cursor.moveToNext());

        }

        return (list);
    }
}
