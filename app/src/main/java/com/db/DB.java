package com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.domain.Account;
import com.domain.Operation;


import java.util.ArrayList;

public class DB {

    private SQLiteDatabase db;

    public DB(Context context){
        DBCore auxdb = new DBCore(context);
        db = auxdb.getWritableDatabase();
    }

    public void createAccount(Account account) {
        ContentValues accountContentValue = new ContentValues();
        accountContentValue.put("name", account.getName());
        accountContentValue.put("isNew", 0);
        db.insert("accounts", null, accountContentValue);

        ContentValues operationContentValue = new ContentValues();
        Operation operation = account.getOperations().get(0);
        operationContentValue.put("value", operation.getValue());
        operationContentValue.put("type", operation.getType());
        operationContentValue.put("name", operation.getName());
        operationContentValue.put("id_accounts", account.getId());
        db.insert("operations", null, operationContentValue);

        db.close();
    }

    public void addOperation(Account account) {
        ContentValues values = new ContentValues();

        for (Operation operation : account.getOperations()) {

            values.put("value", operation.getValue());
            values.put("type", operation.getType());
            values.put("name", operation.getName());
            values.put("id_accounts", account.getId());
        }
        db.insert("operations", null, values);
        db.close();
    }

    public ArrayList<Account> searchAccounts() {
        ArrayList<Account> list = new ArrayList<Account>();
        ArrayList<String>auxResult = new ArrayList<>();
        String[]result;
        String[] columns = new String[]{"_id","name"};

        Cursor cursor = db.query("accounts", columns, null, null, null, null,null);

        if (cursor.getCount() > 0) {
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
            auxResult.toArray(result);
        }
        db.close();
        return (list);
    }

    public  ArrayList<Operation> searchOperations(Account account) {
        ArrayList<Operation> op = new ArrayList<>();

        String id_accounts = Integer.toString(account.getId());
        String[] columns = new String[]{"_id","name","type","value","id_accounts"};

        Cursor cursor = db.query("operations", columns,"id_accounts=?", new String[] {id_accounts},null,null,null);//parametros null indicam clausulas diversas, escolhi apenas order by

        if (cursor.getCount() > 0) { //checa se o cursor encontrou resultados na busca e prossegue
            cursor.moveToFirst();
            do {
                Operation operation = new Operation();
                operation.setId(cursor.getInt(0));
                operation.setName(cursor.getString(1));
                operation.setType(cursor.getString(2));
                operation.setValue(cursor.getDouble(3));
                account.setId(cursor.getInt(4));

                op.add(operation);
                account.setOperations(op);

            } while (cursor.moveToNext());
        }
        db.close();
        return (account.getOperations());
    }

}
