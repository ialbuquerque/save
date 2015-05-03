package com.domain;

import java.util.List;

/**
 * Created by Daniel on 01/05/2015.
 */
public class Account {
    private String name;
    private List<Operator> operators;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }



}
