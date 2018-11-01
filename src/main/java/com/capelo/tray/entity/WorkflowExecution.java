package com.capelo.tray.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkflowExecution extends Thread implements Serializable {

    private long id;

    private int step = 0;

    private Workflow workflow;

    private LocalDateTime timestamp;

    public WorkflowExecution(long id, Workflow workflow){
        this.id =id;
        this.workflow = workflow;
        this.timestamp = LocalDateTime.now();
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private void stepIncrement() {
        step++;
    }

    public int getStep() {
        return step;
    }

    @Override
    public String toString() {
        return "WorkflowExecution{" +
                "id=" + id +
                ", step=" + step +
                ", workflow=" + workflow +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public void run() {

        while(step <= workflow.getSteps()) {
            System.out.println("WorkflowExecution: "+workflow.getId()+ " step: "+ step);

            //Simulating a process
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stepIncrement();

        }

    }
}
