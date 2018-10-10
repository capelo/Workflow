package com.capelo.tray.controller;

import com.capelo.tray.entity.Workflow;
import com.capelo.tray.entity.WorkflowExecution;
import com.capelo.tray.service.WorkflowService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    public Map<Long, WorkflowExecution> listWorkflowExecutions(){
        return workflowService.listWorkflowExecutions();
    }

    public Map<Long, Workflow> listWorkflows(){
        return workflowService.listWorkflows();
    }

    public Workflow getWorkflow(long id){
        return workflowService.getWorkflow(id);
    }

    public void createWorkflowExecution(Workflow workflow){
        workflowService.createWorkflowExecution(workflow);
    }

    public void createWorkflowExecution(String[] workflowIds){
        workflowService.createWorkflowExecution(workflowIds);
    }

    public WorkflowExecution getWorkflowExecution(long id){
        return workflowService.getWorkflowExecution(id);
    }

    public void load(String jsonFile){

        try {
            workflowService.load(jsonFile);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ExecutorService execute(){
        return workflowService.execute();
    }

    public void runWorkflowExecution(String[] args){
        workflowService.createWorkflowExecution(args);
    }

}
