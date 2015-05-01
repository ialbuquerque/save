package com.save;

/**
 * Created by Daniel on 01/05/2015.
 */
public abstract class Operator {
    private String name;
    private double value;

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
    public abstract String getType();
}
