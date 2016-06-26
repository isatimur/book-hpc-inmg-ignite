package com.blu.imdg.dao;

import com.blu.imdg.dto.Employee;

import java.util.List;

/**
 * Created by shamim on 24/06/16.
 */
public interface EmpDao {
    List<Employee> getAllEmployees ();
    void create(Integer empno, String ename, String job, Integer mgr );
}
