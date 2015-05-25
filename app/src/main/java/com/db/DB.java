package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.domain.Account;
import com.domain.Operator;


import java.util.ArrayList;




/**
 * Created by Daniel on 01/05/2015.
 */
public class DB {
    private SQLiteDatabase db;


    public DB(Context context){
        DBCore auxdb = new DBCore(context);
        db = auxdb.getWritableDatabase();

    }

    public void createAccount(Account account){
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", account.getName());

            db.insert("accounts",null,contentValues);
        }catch (Exception e){}

        finally {
            db.close();
        }

    }

    public void addOperation(Account account){

        try {


            ContentValues values = new ContentValues(); //cria um content para enviar a requisição

            for (Operator o : account.getOperators()) {

                values.put("value", o.getValue());
                values.put("type", o.getType());

                //utilizei um for para recuperar as informações do atributo List<Operator> de Account
            }
            //metodo put sai colocando o que se deseja dentro do content, aceita uma string e diversos tipos como String, double, byte, int

            db.insert("operations", null, values);

        }catch (Exception e){

        }
        finally {
            db.close();
        }

    }

    public void refresh(Account account){

        try {


            ContentValues values = new ContentValues(); //cria um content para enviar a requisição
            values.put("name", account.getName());
            for (Operator o : account.getOperators()) {
                values.put("name", o.getName());
                values.put("value", o.getValue());
                values.put("type_operator", o.getType());
                //utilizei um for para recuperar as informações do atributo List<Operator> de Account
            }
            //metodo put sai colocando o que se deseja dentro do content, aceita uma string e diversos tipos como String, double, byte, int

            db.update("accounts", values, "name = ?", new String[]{"" + account.getName()});
            //o terceiro parâmetro permite que você defina as where clauses, e se colocar "?" faz com que isso seja preenchido com o conteúdo do último parâmetro
            //O último parametro permite que se coloque where clauses externas, aqui fiz com uma string mesmo, mas poderia vir de outro lugar
        }catch (Exception e){

        }
        finally {
            db.close();
        }

    }

    public void drop(Account account){
        db.delete("accounts"," name = "+account.getName(),null);
        db.close();
        //aqui ja fiz a where clause dentro do parametro, e o externo passei como null



    }

    public String[] search() {
        ArrayList<String> auxResult = new ArrayList<String>();

        ArrayList<Account> list = new ArrayList<Account>();

        String[] columns = new String[]{"name"};
        String[] result = new String[0];
        try {


            Cursor cursor = db.query("accounts", columns, null, null, null, null,null);//parametros null indicam clausulas diversas, escolhi apenas order by

            if (cursor.getCount() > 0) { //checa se o cursor encontrou resultados na busca e prossegue
                cursor.moveToFirst();

                do {

                    Account a = new Account();
                    a.setName(cursor.getString(0));

                    list.add(a);
                } while (cursor.moveToNext());
                result = new String[list.size()];


                for (int i = 0; i < list.size(); i++) {

                    String aux = list.get(i).getName();
                    auxResult.add(aux);

                }
                result = auxResult.toArray(result);
            }


        } catch (Exception e) {

        } finally {
            db.close();
        }
        return (result);


    }

}
