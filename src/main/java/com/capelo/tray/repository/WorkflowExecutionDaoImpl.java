package com.capelo.tray.repository;

import com.capelo.tray.entity.WorkflowExecution;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class WorkflowExecutionDaoImpl implements WorkflowDaoCrud<Long, WorkflowExecution> {
    public Map<Long, WorkflowExecution> workflowExecutions = new ConcurrentHashMap();

    @Override
    public WorkflowExecution get(Long id) {
        return workflowExecutions.get(id);
    }

    @Override
    public Map<Long, WorkflowExecution> getAll() {
        return workflowExecutions;
    }
    @Override
    public int count() {
        return workflowExecutions.size();
    }

    @Override
    public void insert(WorkflowExecution var1) {
        workflowExecutions.put((long)workflowExecutions.size()+1,var1);
    }
}
