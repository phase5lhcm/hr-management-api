package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.DAO.Employee;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRAuthException;

public interface EmployeeRepository {

    // creates employee id
    Integer createEmployee(String firstName, String lastName, String address, String email, String password) throws HRAuthException;

    Employee findByEmailAndPassword(String email, String password) throws HRAuthException;

    //check if email is already in use or not
    Integer getCountByEmail(String email);

    Employee findById(Integer emplid);
}
