package com.save;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.domain.Account;
import com.domain.Operator;
import com.util.OperationsAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Operations extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        ArrayList<Operator> op1 = new ArrayList<Operator>();

        int layout = R.layout.operations;


        //String[]op = new String[]{"op1", "op2","op3","op4"};

       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,op);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        Account account = new Account();
        ArrayList<Operator>operators = new ArrayList<Operator>();
        Operator op= new Operator();
        op.setName("nome");
        op.setValue(20.3);
        op.setType("tipo");
        for (int i = 0; i <10 ; i++) {
            operators.add(op);

        }

        account.setOperators(operators);
        lv.setAdapter(new OperationsAdapter(this,account.getOperators()));

    }
    public void addIncome(View view){
        finish();
    }
    public void addExpense(View view){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_operations, menu);
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
