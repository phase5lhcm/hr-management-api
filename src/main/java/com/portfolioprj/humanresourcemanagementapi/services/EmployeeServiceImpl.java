package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.exceptions.HRAuthException;
import com.portfolioprj.humanresourcemanagementapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee validateEmployee(String email, String password) throws HRAuthException {
        return null;
    }

    @Override
    public Employee registerEmployee(String firstName, String lastName, String address, String email, String password) throws HRAuthException {
        Pattern matchEmailFormat = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!matchEmailFormat.matcher(email).matches())
            throw new HRAuthException("Please use a real email address!");
        Integer emailExistCount = employeeRepository.getCountByEmail(email);
        if(emailExistCount > 0)
            throw new HRAuthException("That email is already in use. Please login or create a new account");
        Integer emplid = employeeRepository.createEmployee(firstName, lastName,address, email, password);
        return employeeRepository.findById(emplid);
    }
}
