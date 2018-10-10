package com.capelo.tray.service;

import com.capelo.tray.entity.Workflow;
import com.capelo.tray.entity.WorkflowExecution;
import com.capelo.tray.repository.WorkflowDaoImpl;
import com.capelo.tray.repository.WorkflowExecutionDaoImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WorkflowService {

    //ThreadPool for just 2 threads at same time
    ExecutorService executor = Executors.newFixedThreadPool(2);

    @Autowired
    private WorkflowDaoImpl workflowDao;

    @Autowired
    private WorkflowExecutionDaoImpl workflowExecutionDao;

    public Workflow getWorkflow(long id){
        return workflowDao.get(id);
    }

    public WorkflowExecution getWorkflowExecution(long id){
        return workflowExecutionDao.get(id);
    }

    public Map<Long, WorkflowExecution> listWorkflowExecutions(){
        return workflowExecutionDao.getAll();
    }

    public Map<Long, Workflow> listWorkflows(){
        return workflowDao.getAll();
    }

    public void createWorkflow(Workflow workflow) {
        workflowDao.insert(workflow);
    }
    public void load(String jsonFile) throws IOException{
        ObjectMapper mapper = new ObjectMapper();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(jsonFile).getFile());

        JsonNode rootNode = mapper.readTree(file);

        JsonNode workflows = rootNode.path("workflows");
        Iterator<JsonNode> elements = workflows.elements();
        while(elements.hasNext()){
            JsonNode workflowNode = elements.next();

            Workflow workflow = mapper.readValue(workflowNode.toString(), Workflow.class);
            createWorkflow(workflow);
        }


    }
    public void createWorkflowExecution(Workflow workflow) {
        WorkflowExecution workflowExecution = new WorkflowExecution(workflowExecutionDao.count()+1, workflow);
        workflowExecutionDao.insert(workflowExecution);
    }

    public void createWorkflowExecution(String[] workflowIds) {

        for (String id: workflowIds) {
            Workflow workflow = workflowDao.get(Long.parseLong(id));
            if (workflow != null) {
                createWorkflowExecution(workflow);
            }
        }
    }

    public ExecutorService execute(){
        workflowExecutionDao.getAll().values()
                        .stream()
                        .forEach(w -> executor.submit(w));

        return executor;
   }

}
