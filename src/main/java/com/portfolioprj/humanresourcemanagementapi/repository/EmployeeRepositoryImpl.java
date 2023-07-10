package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.exceptions.HRAuthException;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public Integer createEmployee(String firstName, String lastName, String email, String password) throws HRAuthException {
        return null;
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) throws HRAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public Employee findById(Integer emplid) {
        return null;
    }
}
