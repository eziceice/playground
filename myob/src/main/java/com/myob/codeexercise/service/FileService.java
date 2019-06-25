package com.myob.codeexercise.service;

import com.myob.codeexercise.pojo.Salary;

import java.util.List;

/**
 * Interface for file service
 */
public interface FileService {
    List<Salary> load();

    void write(List<Salary> salaryList);
}

