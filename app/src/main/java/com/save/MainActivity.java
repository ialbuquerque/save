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
import android.widget.ListView;
import android.widget.TextView;


import com.domain.Account;
import com.util.AccountAdapter;
import com.util.ActivitySupport;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivitySupport as = new ActivitySupport(this);


        TextView tv = (TextView) findViewById(R.id.tv_main);
        tv.setText("Selecione uma conta");

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
        ArrayList<Account>accounts = as.getAccounts();
        lv.setAdapter(new AccountAdapter(this,accounts));

        lv.setOnItemClickListener(openOperations(accounts));



    }
    public AdapterView.OnItemClickListener openOperations(final ArrayList<Account> accounts ){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"erro "+accounts.get(position).getName());
                Log.d(TAG,"erro "+accounts.get(position).getId());

                //Toast.makeText(getApplicationContext(), a.get(position).getId(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getBaseContext(), Operations.class);
                intent.putExtra("account", accounts.get(position));



                startActivity(intent);
            }
        });
    }
    public void addAccount(View view){
        Intent intent = new Intent(this, AddAccount.class);
        startActivity(intent);
        finish();
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
