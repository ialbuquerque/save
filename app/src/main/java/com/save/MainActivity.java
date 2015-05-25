package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.db.DB;
import com.domain.Account;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB db = new DB(this); //instanciei o bd
        String[] accounts;
        accounts=db.search();

       // for (int i = 0; i < accounts.length; i++){
        //    Toast.makeText(this,accounts[i],Toast.LENGTH_SHORT).show();

        //}







        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,accounts);

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
        lv.setAdapter(arrayAdapter);

    }


    public void addAccount(View view){
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
        finish();
    }
    public  void myGoals(View view){
        Intent intent = new Intent(this, MyGoals.class);
        startActivity(intent);
    }
    public void addExpenseByMain(View view){
        Intent intent = new Intent(this, AddExpenseByMain.class);
        startActivity(intent);
    }
    public void addIncomeByMain(View view){
        Intent intent = new Intent(this, AddIncomeByMain.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
