package com.save;

import android.content.Intent;
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
import com.util.ActivitySupport;
import com.util.OperationsAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Operations extends ActionBarActivity {
    private String name;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        ActivitySupport as = new ActivitySupport(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
            name = extras.getString("name");


            if (name != null) {
                as.getAccountClicked(id, name);
            }
        }








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
       // ArrayList<Operator> op = as.getAccountClicked(id,name).getOperators();
        ArrayList<Operator> op = as.getAccounts().get(1).getOperators();



        lv.setAdapter(new OperationsAdapter(this,op));

    }
    public void addOperation(View view){
        Intent intent = new Intent(this, AddOperation.class);
        startActivity(intent);
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
