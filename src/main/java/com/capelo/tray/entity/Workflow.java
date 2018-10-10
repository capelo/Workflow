package com.capelo.tray.entity;

import java.io.Serializable;

public class Workflow implements Serializable {

    private long id;

    private int steps;

    public Workflow(){

    }

    public Workflow(long id, int steps) {
        this.id = id;
        this.steps = steps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id=" + id +
                ", steps=" + steps +
                '}';
    }
}
