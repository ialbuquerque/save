package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.domain.Account;
import com.util.AccountAdapter;
import com.util.ActivitySupport;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivitySupport as = new ActivitySupport(this);



        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,as.getAccounts());

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
        lv.setAdapter(new AccountAdapter(this,as.getAccounts()));

        lv.setOnItemClickListener(openOperations(as));



    }
    public AdapterView.OnItemClickListener openOperations(final ActivitySupport as ){
        return (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(), a.get(position).getId(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getBaseContext(), Operations.class);
                Bundle extras = intent.getExtras();
                extras.putInt("id", as.getAccounts().get(position).getId());
                extras.putString("name",as.getAccounts().get(position).getName());

                intent.putExtras(extras);

                startActivity(intent);
            }
        });
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
