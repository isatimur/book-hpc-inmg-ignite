package com.blu.imdg;

import com.blu.imdg.dao.EmpDao;
import com.blu.imdg.dto.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by shamim on 24/06/16.
 */
public class EmpDaoImpl implements EmpDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Employee");
        List<Employee> employees =  query.list();
        session.close();
        if(employees != null && !employees.isEmpty()){
            return employees;
        }
        return Collections.emptyList();
    }

    public void create(Integer empno, String ename, String job, Integer mgr) {
        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("insert into Employee(empno, ename, job, mgr) VALUES (?,?,?,?)");
//        query.setInteger("empno", empno);
//        query.setString("ename", "test");
//        query.setString("job", "clark");
//        query.setInteger("mgr",mgr);
//        query.executeUpdate();
//        session.close();
        session.beginTransaction();
        Employee emp = new Employee();
        emp.setEmpno(empno);
        emp.setEname(ename);
        emp.setJob(job);
        emp.setMgr(mgr);
        session.save(emp);
        session.getTransaction().commit();
        session.close();
    }
}
