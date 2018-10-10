package com.capelo.tray.repository;

import com.capelo.tray.entity.Workflow;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class WorkflowDaoImpl implements WorkflowDaoCrud<Long, Workflow> {
    private Map<Long, Workflow> workflows = new ConcurrentHashMap();

    @Override
    public Map<Long, Workflow> getAll() {
        return workflows;
    }

    @Override
    public Workflow get(Long id) {
        return workflows.get(id);
    }

    @Override
    public int count() {
        return workflows.size();
    }

    @Override
    public void insert(Workflow var1) {
        workflows.put(var1.getId(), var1);
    }

}
