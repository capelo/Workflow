# Workflow simulation task

## Introduction

CommandLine application using Spring to handle the best project structure and testability. 

## Pre-requisites

1. Apache Maven
2. Java 8

## Running
Please use the following command to build and run the application:

```mvn clean install -DSkipTests```

```mvn spring-boot:run``` 

## Unit tests
Please use the following command to run unit and integration tests:

```mvn test```

### Console/Terminal Commands:

reload -> create -> execute

(reload) - Read the workflows from file
 
(create)  - Create workflowexecutions of selected id workflows

(execute) - Execute each workflowexecution increasing the stepindex attribute
 
### Show help
Description: Show all commands to create, execute and verify workflows  

Command: help 

````
Workflows available: 
         Command           Description                    
         help              show this helper               
         reload            reload the workflow file(json) 
         execute           run all workflows created      
         workflows         show the workflows available   
         available        show all workflowExecutions     
         create            create the workflowExecutions 
````

### Create workflow:
Description: Read and create workflows from the file resources/json/file.json

Command: reload

### Show workflows:
Description: List all workflows available to be executed (creating a new workflow execution)

Command: workflows
````
TYPE YOUR OPTION
workflows
Workflow{id=1, steps=100}
Workflow{id=2, steps=50}
Workflow{id=3, steps=200}
Workflow{id=4, steps=10}
Workflow{id=5, steps=400}
Workflow{id=6, steps=100}
````
### Create workflowExecution
Description: Create workflow executions from selected workflow id previously created.

Command: create

After the command create is showed all workflows available, just type the workflows id separated by space to create the workflow execution.

Example:

  ````
  TYPE YOUR OPTION
  workflows
  
  Workflow{id=1, steps=100}
  Workflow{id=2, steps=50}
  Workflow{id=3, steps=200}
  Workflow{id=4, steps=10}
  Workflow{id=5, steps=400}
  Workflow{id=6, steps=100}
  
  TYPE YOUR OPTION
  
  create
  
  Select the (workflows id) to be executed. Example: 1 2 3
  1 2
  
  Running Workflows :1 2
  WorkflowExecutions ready to run, type EXECUTE
  TYPE YOUR OPTION
  ````

### Show workflow execution available

Description: Show all workflowexecution (workflows with step=0 are workflowexecution available to run)

Command: available

````
  TYPE YOUR OPTION
  available
  WorkflowExecution{id=1, step=0, workflow=Workflow{id=1, steps=100}, timestamp=2018-10-10T12:01:02.531}
  WorkflowExecution{id=2, step=0, workflow=Workflow{id=2, steps=50}, timestamp=2018-10-10T12:01:02.533}
````

### Execute workflowexecutions available

Description: Run all workflowexecutions that was not executed yet (step=0)

All the workflowexecutions are running ate same time in a pool of just 2 at same time 

You can chek the workflows executions after the process typing the command: available 

Command: execute


````
TYPE YOUR OPTION
execute
WorkflowExecution: 1 step: 0
TYPE YOUR OPTION
WorkflowExecution: 2 step: 0
WorkflowExecution: 1 step: 1
WorkflowExecution: 2 step: 1
...
available
WorkflowExecution{id=1, step=100, workflow=Workflow{id=1, steps=100}, timestamp=2018-10-10T12:01:02.531}
WorkflowExecution{id=2, step=50, workflow=Workflow{id=2, steps=50}, timestamp=2018-10-10T12:01:02.533}
````
