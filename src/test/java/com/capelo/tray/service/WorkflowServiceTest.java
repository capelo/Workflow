package com.capelo.tray.service;

import com.capelo.tray.controller.WorkflowController;
import com.capelo.tray.entity.Workflow;
import com.capelo.tray.entity.WorkflowExecution;
import com.capelo.tray.repository.WorkflowDaoImpl;
import com.capelo.tray.repository.WorkflowExecutionDaoImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes={WorkflowController.class, WorkflowService.class, WorkflowDaoImpl.class, WorkflowExecutionDaoImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Profile("test")
public class WorkflowServiceTest {

    @Autowired
    private WorkflowController workflowController;

    @Before
    public void setup(){
        workflowController.load("json/test.json");
    }

    @Test
    public void createWorkflowFromJsonFileShouldLoad2Workflows(){
        Assert.assertEquals(workflowController.listWorkflows().size(), 2);
    }

    @Test
    public void loadWorkflowObject(){
        Assert.assertNotNull(workflowController.getWorkflow(1));
    }

    @Test
    public void createWorkflowExecutionFromWorkflow(){

        Workflow workflowTest = new Workflow(9, 90);

        workflowController.createWorkflowExecution(workflowTest);

        workflowController.getWorkflowExecution(workflowController.listWorkflowExecutions().size()+1);

    }

    @Test
    public void runWorkflowExecution2WorkflowsShouldProcess55And60Steps(){

        Workflow workflow55Steps = new Workflow(10, 55);
        Workflow workflow60Steps = new Workflow(19, 60);

        workflowController.createWorkflowExecution(workflow55Steps);
        workflowController.createWorkflowExecution(workflow60Steps);

        ExecutorService taskExecutor = workflowController.execute();

        taskExecutor.shutdown();
        try {
            taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WorkflowExecution workflowExecution55 = workflowController.getWorkflowExecution(workflowController.listWorkflowExecutions().size()-1);
        WorkflowExecution workflowExecution60 = workflowController.getWorkflowExecution(workflowController.listWorkflowExecutions().size());

        Assert.assertEquals(workflowExecution55.getStep(), 55);
        Assert.assertEquals(workflowExecution60.getStep(), 60);
    }

    @Test
    public void runWorkflowExecutionShouldNotRunNoWorkflowExecutionCreated(){

        int workflowExecutionsCreated = workflowController.listWorkflowExecutions().size();
        workflowController.execute();

        Assert.assertEquals(workflowExecutionsCreated, workflowController.listWorkflowExecutions().size());

    }

    @Test
    public void createWorkflowExecutionWithNoWorkflowCreated() {
        int workflowExecutionsCreated = workflowController.listWorkflowExecutions().size();
        String[] workflowId ={"1000"};
        workflowController.createWorkflowExecution(workflowId);

        Assert.assertEquals(workflowExecutionsCreated, workflowController.listWorkflowExecutions().size());

    }

}
