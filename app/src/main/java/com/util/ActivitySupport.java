package com.util;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.db.DB;
import com.domain.Account;
import com.domain.Operator;
import com.save.Operations;

import java.util.ArrayList;

public class ActivitySupport {
    private Context context;
    private DB db;
    private static final String TAG = "ActivitySupport";

    public ActivitySupport(Context context) {
        this.db = new DB(context);
    }

    //retorna o array de contas do listview
    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts;
        accounts = db.searchAccounts();

        return (accounts);
    }

    //chama createAccount() passando o EditText definido na tela
    //e um novo Context para permitir que o Toast seja exibido.
    public void saveAccount(EditText editText1, Context context) {
        Account account = new Account();
        account.setName(editText1.getText().toString());

        db.createAccount(account);

        ArrayList<Operator> op = new ArrayList<>();

        Operator firstOperator = new Operator();
        firstOperator.setId(account.getId());
        firstOperator.setType("Receita");
        firstOperator.setValue(0);
        firstOperator.setName("Saldo Inicial");

        op.add(firstOperator);
        account.setOperators(op);

        db.addOperation(account);

        Log.d(TAG, account.getName() + " " + account.getOperators().get(0).getValue() + " " + account.getOperators().get(0).getId());
        Toast.makeText(context, account.getName() + " adicionado com sucesso", Toast.LENGTH_LONG).show();
    }

    public Account saveIncome(Account account, EditText et1, EditText et2) {
        Operator operator = new Operator();
        operator.setName(et1.getText().toString());
        operator.setValue(Double.valueOf(et2.getText().toString()));
        operator.setType("Receita");

        account.getOperators().add(operator);
        Log.d(TAG, account.getName() + " " + account.getId() + " " + account.getOperators().get(0).getName());

        db.addOperation(account);

        return account;
    }

    public Account saveExpense(Account account, EditText et1, EditText et2) {
        Operator operator = new Operator();
        operator.setName(et1.getText().toString());
        operator.setValue(Double.valueOf(et2.getText().toString()));
        operator.setType("Despesa");

        ArrayList<Operator> op = new ArrayList<Operator>();
        op.add(operator);

        account.setOperators(op);
        Log.d(TAG, account.getName() + " " + account.getId() + " " + account.getOperators().get(0).getName());

        db.addOperation(account);

        return account;
    }

    public ArrayList<Operator> getOperations(Account account) {
        ArrayList<Operator> operators = db.searchOperations(account);
        account.setOperators(operators);

        return (account.getOperators());
    }
}
