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
        TextView tv = (TextView) findViewById(R.id.addOperationTitle);
        tv.setText("Adicionar Receita");
    }

    public void saveOperation(View view){
        Account account = (Account) getIntent().getSerializableExtra("account");
        ActivitySupport as = new ActivitySupport(this);
        as.isNew = false;

        EditText et1 = (EditText) findViewById(R.id.et_name);
        EditText et2 = (EditText) findViewById(R.id.et_value);

        account=as.saveIncome(account, et1, et2);
        Toast.makeText(this, "Sua operação foi salva com sucesso", Toast.LENGTH_LONG);

        Intent intent = new Intent(this, Operations.class);
        intent.putExtra("account",account);
        startActivity(intent);

        finish();
    }

    public void goMain(View view) {
        finish();
    }
}
