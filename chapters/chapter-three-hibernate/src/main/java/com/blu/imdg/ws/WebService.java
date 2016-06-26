package com.blu.imdg.ws;

import com.blu.imdg.dao.EmpDao;
import com.blu.imdg.dto.Employee;

import javax.jws.WebMethod;
import java.util.List;

/**
 * Created by shamim on 24/06/16.
 */
@javax.jws.WebService(name = "BusinessRulesServices",
        serviceName="BusinessRulesServices",
        targetNamespace = "http://com.blu.rules/services")
public class WebService {
    private EmpDao empDao;

    @WebMethod(exclude = true)
    public void setEmpDao(EmpDao empDao) {
        this.empDao = empDao;
    }
    @WebMethod(operationName = "getAllEmploees")
    public List<Employee> getAllEmployees() {
        return empDao.getAllEmployees();
    }
    @WebMethod(operationName = "addEmployee")
    public void addEmployee(Integer empno, String ename, String job, Integer mgr ) {
        empDao.create(empno, ename, job, mgr);
    }
}

