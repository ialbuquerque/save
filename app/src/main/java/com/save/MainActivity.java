package com.save;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.domain.Account;
import com.util.AccountAdapter;
import com.util.ActivitySupport;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeader();

        ListView accountsList = (ListView) findViewById(R.id.lv);
        accountsList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        showAccounts(new ActivitySupport(this), accountsList);
    }

    public void addAccount(View view) {
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
        finish();
    }

    public OnItemClickListener openOperations(final ArrayList<Account> accounts) {
        return (new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), Operations.class);
                intent.putExtra("account", accounts.get(position));
                startActivity(intent);
            }
        });
    }

    private void setHeader() {
        TextView header = (TextView) findViewById(R.id.tv_main);
        header.setText("Selecione uma conta");
    }

    private void showAccounts(ActivitySupport activitySupport, ListView accountsList) {
        ArrayList<Account> accounts = activitySupport.getAccounts();
        accountsList.setAdapter(new AccountAdapter(this, accounts));

        accountsList.setOnItemClickListener(openOperations(accounts));
    }
}
