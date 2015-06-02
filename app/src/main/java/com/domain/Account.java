package com.domain;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 01/05/2015.
 */
public class Account implements Serializable {


    private int id;
    private String name;
    private ArrayList<Operator> operators;

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

    public ArrayList<Operator> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<Operator> operators) {
        this.operators = operators;
    }



}
