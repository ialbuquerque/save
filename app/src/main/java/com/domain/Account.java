package com.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {


    private int id;
    private String name;
    private ArrayList<Operation> operations;
    private int isNew;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public double getBalance() {
        balance = 0;
        for (Operation op : this.getOperations()) {
            balance += op.getValue();
        }
        return balance;
    }
}
