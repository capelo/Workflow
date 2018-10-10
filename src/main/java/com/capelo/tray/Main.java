package com.capelo.tray;

import com.capelo.tray.controller.WorkflowController;
import com.capelo.tray.repository.WorkflowDaoImpl;
import com.capelo.tray.repository.WorkflowExecutionDaoImpl;
import com.capelo.tray.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@ComponentScan
public class Main implements CommandLineRunner {

    @Autowired
    private WorkflowController controller;

    public static void main(String args[]){
        SpringApplication.run(Main.class, args);
    }



    private void help(){
        System.out.println("         Command           Description                    ");
        System.out.println("         help              show this helper               ");
        System.out.println("         reload            reload the workflow file(json) ");
        System.out.println("         execute           run all workflows created      ");
        System.out.println("         workflows         show the workflows available   ");
        System.out.println("         available        show all workflowExecutions     ");
        System.out.println("         create            create the workflowExecutions  ");
    }
    @Override
    public void run(String... strings) throws Exception {

        System.out.println("LOADING Workflows from file.json...");
        controller.load("json/file.json");
        System.out.println("Workflows available: ");
        controller.listWorkflows();
        help();

        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("TYPE YOUR OPTION");

            String str = scanner.nextLine();
            if ("help".equalsIgnoreCase(str)){
                help();
            }
            if ("reload".equalsIgnoreCase(str)){
                controller.load("json/file.json");
                controller.listWorkflows();
            }
            if ("execute".equalsIgnoreCase(str)) {
                controller.execute();
            }
            if ("workflows".equalsIgnoreCase(str)) {
                controller.listWorkflows().values()
                        .stream()
                        .forEach(System.out::println);
            }
            if ("available".equalsIgnoreCase(str)) {
                controller.listWorkflowExecutions().values()
                        .stream()
                        .forEach(System.out::println);
            }
            if ("create".equalsIgnoreCase(str)) {
                controller.listWorkflows();
                System.out.println("Select the (workflows id) to be executed. Example: 1 2 3");
                Scanner scannerExecute = new Scanner(System.in);
                String strExecute = scanner.nextLine();
                System.out.println("Running Workflows :"+strExecute);
                controller.runWorkflowExecution(strExecute.split(" "));
                controller.listWorkflowExecutions();

                System.out.println("WorkflowExecutions ready to run, type EXECUTE");
            }

        }
    }


}