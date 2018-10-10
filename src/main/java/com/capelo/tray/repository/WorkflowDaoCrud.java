package com.capelo.tray.repository;


import java.util.Map;

public interface WorkflowDaoCrud<ID, T> {

    Map<ID, T> getAll();

    void insert(T var1);

    T get(ID id);

    int count();

}
