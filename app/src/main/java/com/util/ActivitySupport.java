package com.util;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.db.DB;
import com.domain.Account;
import com.domain.Operator;
import com.save.Operations;

import java.util.ArrayList;

/**
 * Created by Daniel on 25/05/2015.
 */
public class ActivitySupport {
    private Context context;
    private DB db;

    public ActivitySupport(Context context){
        this.db=new DB(context);
    }

    public Account getAccountClicked(int id, String name){

        Account account = new Account();
        account.setId(id);
        account.setName(name);


        if (db.searchAccounts().contains(account)==false){
            return (null);

        }


    return (account);}

    public ArrayList<Account> getAccounts(){

        ArrayList<Account> accounts;
        accounts=db.searchAccounts();



        return (accounts);
    }//retorna o array de contas do listview

    public void saveAccount(EditText editText, Context context){

        Account account = new Account();
        account.setName(editText.getText().toString());

        db.createAccount(account);
        Toast.makeText(context,account.getName()+" adicionado com sucesso",Toast.LENGTH_LONG).show();




    }//chama createAccount() passando o EditText definido na tela e um novo Context para permitir que o Toast seja exibido.
    public void saveOperation(EditText et1, EditText et2, CheckBox cb1, CheckBox cb2, Context context){
        Operator operator = new Operator();
        operator.setName(et1.getText().toString());
        operator.setValue(Double.valueOf(et2.getText().toString()));
        cb1.setActivated(true);
        cb2.setActivated(false);

        if(cb1.isChecked()){
            operator.setName(cb1.getText().toString());

        }
        else cb2.isChecked();{

            operator.setName(cb2.getText().toString());
        }
        ArrayList<Operator>op = new ArrayList<Operator>();
        op.add(operator);
        Account account = new Account();
        account.setOperators(op);

        db.addOperation(account);
        Toast.makeText(context,operator.getName(),Toast.LENGTH_LONG);

        
    }

    public ArrayList<Operator> getOperations(Account account){
        ArrayList<Operator>operators = new ArrayList<Operator>();
        db.searchOperations();




    return(operators);}












}
