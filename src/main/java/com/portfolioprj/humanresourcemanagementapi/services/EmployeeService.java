package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.helpers.HRAuthException;

public interface EmployeeService {
    Employee validateEmployee(String email, String password) throws HRAuthException;

    Employee registerEmployee(String firstName, String lastName, String address, String email,
                              String password) throws HRAuthException;
}
