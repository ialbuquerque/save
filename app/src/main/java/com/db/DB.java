package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.domain.Account;
import com.domain.Operator;


import java.util.ArrayList;
import java.util.Collection;

public class DB {

    private SQLiteDatabase db;

    public DB(Context context){
        DBCore auxdb = new DBCore(context);
        db = auxdb.getWritableDatabase();

    }

    public void createAccount(Account account){
        try {
            ContentValues accountContentValue = new ContentValues();
            accountContentValue.put("name", account.getName());
            accountContentValue.put("isNew", 0);
            db.insert("accounts", null, accountContentValue);

            ContentValues operationContentValue = new ContentValues();
            Operator operator = account.getOperators().get(0);
            operationContentValue.put("value", operator.getValue());
            operationContentValue.put("type", operator.getType());
            operationContentValue.put("name", operator.getName());
            operationContentValue.put("id_accounts", account.getId());
            db.insert("operations", null, operationContentValue);
        }catch (Exception e){}

        finally {
            db.close();
        }
    }

    public void addOperation(Account account){

        try {
            int accId = account.getId();

            ContentValues values = new ContentValues(); //cria um content para enviar a requisição


            for (Operator o : account.getOperators()) {

                values.put("value", o.getValue());
                values.put("type", o.getType());
                values.put("name",o.getName());
                values.put("id_accounts",accId);

                //utilizei um for para recuperar as informações do atributo List<Operator> de Account
            }
            //metodo put sai colocando o que se deseja dentro do content, aceita diversos tipos como String, double, byte, int, long

            db.insert("operations", null, values);

        }catch (Exception e){

        }
        finally {
            db.close();
        }

    }

    public ArrayList<Account> searchAccounts() {


        ArrayList<Account> list = new ArrayList<Account>();
        ArrayList<String>auxResult = new ArrayList<>();
        String[]result = new String[0] ;

        String[] columns = new String[]{"_id","name"};

        try {


            Cursor cursor = db.query("accounts", columns, null, null, null, null,null);//parametros null indicam clausulas diversas, escolhi apenas order by

            if (cursor.getCount() > 0) { //checa se o cursor encontrou resultados na busca e prossegue
                cursor.moveToFirst();

                do {

                    Account a = new Account();
                    a.setId(cursor.getInt(0));
                    a.setName(cursor.getString(1));

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
        return (list);


    }

    public  ArrayList<Operator> searchOperations(Account account){
        ArrayList<Operator> op = new ArrayList<>();

        String id_accounts = Integer.toString(account.getId());


        String[] columns = new String[]{"_id","name","type","value","id_accounts"};

        try {


            Cursor cursor = db.query("operations", columns,"id_accounts=?", new String[] {id_accounts},null,null,null);//parametros null indicam clausulas diversas, escolhi apenas order by

            if (cursor.getCount() > 0) { //checa se o cursor encontrou resultados na busca e prossegue
                cursor.moveToFirst();

                do {

                    Operator o = new Operator();


                    o.setId(cursor.getInt(0));
                    o.setName(cursor.getString(1));
                    o.setType(cursor.getString(2));
                    o.setValue(cursor.getDouble(3));
                    account.setId(cursor.getInt(4));





                    op.add(o);
                    account.setOperators(op);
                } while (cursor.moveToNext());

            }


        } catch (Exception e) {

        } finally {
            db.close();
        }
        return (account.getOperators());

    }

}
