package com.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;


import com.db.DB;
import com.domain.Account;
import com.save.R;

/**
 * Created by Daniel on 25/05/2015.
 */
public class ActivitySupport {
    private Context context;
    private DB db;

    public ActivitySupport(Context context){
        this.db=new DB(context);
    }

    public String[] getAccounts(){
        String[] accounts;
        accounts=db.search();
        return (accounts);
    }//retorna o array de contas do listview

    public void saveAccount(EditText editText, Context context){

        Account account = new Account();
        account.setName(editText.getText().toString());

        db.createAccount(account);
        Toast.makeText(context,account.getName()+" adicionado com sucesso",Toast.LENGTH_LONG).show();




    }//chama createAccount() passando o EditText definido na tela e um novo Context para permitir que o Toast seja exibido.










}
