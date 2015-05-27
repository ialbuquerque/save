package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 01/05/2015.
 */
public class Account {
    private String name;
    private ArrayList<Operator> operators;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Operator> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<Operator> operators) {
        this.operators = operators;
    }



}
