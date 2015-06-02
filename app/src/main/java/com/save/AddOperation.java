package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.domain.Account;
import com.util.ActivitySupport;


public class AddOperation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);
    }
    public void saveOperation(View view){
        ActivitySupport as = new ActivitySupport(this);

        EditText et1 = (EditText) findViewById(R.id.et_name);
        EditText et2 = (EditText) findViewById(R.id.et_value);
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox_expense);
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox_income);
        as.saveOperation(et1,et2,cb1,cb2,this);

        Intent intent = new Intent(this, Operations.class);
        startActivity(intent);
        finish();
    }

    public void goMain(View view) {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_income_by_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
