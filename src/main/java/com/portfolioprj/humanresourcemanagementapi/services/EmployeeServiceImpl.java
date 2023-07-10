package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.exceptions.HRAuthException;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee validateEmployee(String email, String password) throws HRAuthException {
        return null;
    }

    @Override
    public Employee registerEmployee(String firstName, String lastName, String email, String password) throws HRAuthException {
        return null;
    }
}
