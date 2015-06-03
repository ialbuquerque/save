package com.domain;

import java.io.Serializable;

/**
 * Created by Daniel on 01/05/2015.
 */
public class Operator implements Serializable {


    private int id;
    private String name;
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public String getType(){
        return type; }
}
