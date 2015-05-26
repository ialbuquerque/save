package com.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.db.DB;

/**
 * Created by Daniel on 25/05/2015.
 */
public class MainSupport {
    private Context context;
    private DB db;

    public MainSupport(Context context){
        this.db=new DB(context);
    }

    public String[] getAccounts(){
        String[] accounts;
        accounts=db.search();
        return (accounts);
    }//retorna o array de contas do listview









}
