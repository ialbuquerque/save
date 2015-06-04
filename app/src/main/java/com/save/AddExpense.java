package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.Account;
import com.util.ActivitySupport;


public class AddExpense extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);
        TextView tv = (TextView) findViewById(R.id.addOperationTitle);
        tv.setText("Adicionar Despesa");
    }
    public void saveOperation(View view){
        Account account = (Account) getIntent().getSerializableExtra("account");


        ActivitySupport as = new ActivitySupport(this);

        EditText et1 = (EditText) findViewById(R.id.et_name);
        EditText et2 = (EditText) findViewById(R.id.et_value);

        account=as.saveExpense(account, et1, et2);
        Toast.makeText(this, "Sua operação foi salva com sucesso", Toast.LENGTH_LONG);

        Intent intent = new Intent(this, Operations.class);
        intent.putExtra("account",account);
        startActivity(intent);

        finish();
    }

    public void goMain(View view) {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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
