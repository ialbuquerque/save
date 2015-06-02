package com.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.Account;
import com.save.R;

import java.util.ArrayList;

/**
 * Created by Daniel on 27/05/2015.
 */
public class AccountAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Account> account;

    public AccountAdapter(Context context, ArrayList<Account> account){
        this.context=context;
        this.account=account;

    }
    @Override
    public int getCount() {
        return account.size();
    }

    @Override
    public Object getItem(int position) {
        return account.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.accounts,null);
        TextView tv1 = (TextView) layout.findViewById(R.id.tv_id);
        TextView tv2 = (TextView) layout.findViewById(R.id.tv_account);

        tv1.setText(Integer.toString(account.get(position).getId()));
        tv2.setText(account.get(position).getName());






        return (layout);
    }
}
