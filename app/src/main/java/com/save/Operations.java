package com.save;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
        ActivitySupport activitySupport = new ActivitySupport(this);

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

        ArrayList<Operator> op = getOperators(activitySupport, account);

    lv.setAdapter(new OperationsAdapter(this,op));
}

    private ArrayList<Operator> getOperators(ActivitySupport activitySupport, Account account) {
        ArrayList<Operator> op = new ArrayList<>();
        if (account.getIsNew() == 0) {
            Operator firstOperator = new Operator();
            firstOperator.setId(account.getId());
            firstOperator.setType("Receita");
            firstOperator.setValue(0);
            firstOperator.setName("Saldo Inicial");

            op.add(firstOperator);
            account.setOperators(op);
            account.setIsNew(1);
        } else {
            op = activitySupport.getOperations(account);
        }
        return op;
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
}
