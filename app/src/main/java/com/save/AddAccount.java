package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.db.DB;
import com.domain.Account;
import com.util.ActivitySupport;


public class AddAccount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
    }

    public void createAccount(View view) {
        EditText editText1 = (EditText) findViewById(R.id.accountEditText);

        ActivitySupport as = new ActivitySupport(this);
        as.saveAccount(editText1, this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
