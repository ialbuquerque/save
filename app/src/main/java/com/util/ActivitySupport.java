package com.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.db.DB;
import com.domain.Account;
import com.domain.Operation;

import java.util.ArrayList;

public class ActivitySupport {
    private Context context;
    private DB db;

    public ActivitySupport(Context context) {
        this.db = new DB(context);
    }

    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts;
        accounts = db.searchAccounts();

        return (accounts);
    }

    public void saveAccount(EditText editText, Context context) {
        Account account = new Account();
        account.setName(editText.getText().toString());
        account.setOperations(createInitialOperation(account));
        db.createAccount(account);

        Toast.makeText(context, account.getName() + " adicionada com sucesso", Toast.LENGTH_LONG).show();
    }

    private ArrayList<Operation> createInitialOperation(Account account) {
        ArrayList<Operation> op = new ArrayList<>();
        Operation firstOperation = new Operation();

        firstOperation.setId(account.getId());
        firstOperation.setType("Receita");
        firstOperation.setValue(0);
        firstOperation.setName("Saldo Inicial");
        op.add(firstOperation);

        return op;
    }

    public Account saveIncome(Account account, EditText operationName, EditText operationValue) {
        Operation operation = new Operation();
        operation.setName(operationName.getText().toString());
        operation.setValue(Double.valueOf(operationValue.getText().toString()));
        operation.setType("Receita");

        account.getOperations().add(operation);

        db.addOperation(account);

        return account;
    }

    public Account saveExpense(Account account, EditText operationName, EditText operationValue) {
        Operation operation = new Operation();
        operation.setName(operationName.getText().toString());
        operation.setValue((Double.valueOf(operationValue.getText().toString())) * -1);
        operation.setType("Despesa");

        account.getOperations().add(operation);

        db.addOperation(account);

        return account;
    }

    public ArrayList<Operation> getOperations(Account account) {
        ArrayList<Operation> operations = db.searchOperations(account);
        account.setOperations(operations);

        return (account.getOperations());
    }
}
