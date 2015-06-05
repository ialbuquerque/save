package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.domain.Account;
import com.domain.Operator;
import com.util.ActivitySupport;
import com.util.OperationsAdapter;

import java.util.ArrayList;


public class Operations extends ActionBarActivity {
    private static final String TAG = "Operations";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        ActivitySupport as = new ActivitySupport(this);

        Account account = (Account) getIntent().getSerializableExtra("account");
        Log.d(TAG, account.getName() + " " + account.getId());

        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(account.getName());

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

        ArrayList<Operator> op = as.getOperations(account);
        //Log.d(TAG,op.get(0).getName()+" "+op.get(0).getValue()+" "+op.get(0).getValue()+" ");

        if (op == null || op.size() == 0) {
            Operator firstOperator = new Operator();
            firstOperator.setId(account.getId());
            firstOperator.setType("Receita");
            firstOperator.setValue(0);
            firstOperator.setName("Saldo Inicial");

            op.add(firstOperator);
            account.setOperators(op);
        }

        lv.setAdapter(new OperationsAdapter(this, op));


    }

    public void addIncome(View view) {
        Account account = (Account) getIntent().getSerializableExtra("account");


        Intent intent = new Intent(this, AddIncome.class);
        intent.putExtra("account", account);

        startActivity(intent);
        finish();
    }

    public void addExpense(View view) {
        Account account = (Account) getIntent().getSerializableExtra("account");


        Intent intent = new Intent(this, AddExpense.class);
        intent.putExtra("account", account);

        startActivity(intent);
        finish();

    }

    public void back(View view) {
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
