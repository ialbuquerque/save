package com.save;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.Account;
import com.util.ActivitySupport;


public class AddIncome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);




    }
    public void saveOperation(View view){
        Account account = (Account) getIntent().getSerializableExtra("account");
        TextView tv = (TextView) findViewById(R.id.addOperationTitle);
        tv.setText("Adicionar Receita");

        ActivitySupport as = new ActivitySupport(this);

        EditText et1 = (EditText) findViewById(R.id.et_name);
        EditText et2 = (EditText) findViewById(R.id.et_value);

        account=as.saveIncome(account, et1, et2);

        Intent intent = new Intent(this, Operations.class);
        intent.putExtra("account",account);
        startActivity(intent);
        Toast.makeText(this, "Sua operação foi salva com sucesso", Toast.LENGTH_LONG);
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
