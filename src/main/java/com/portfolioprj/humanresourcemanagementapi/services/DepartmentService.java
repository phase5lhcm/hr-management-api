package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> fetchAllDepartments();

    Department fetchEmployeeDepartmentByEmplid(Integer emplid) throws HRDeptResourceNotFoundException;

    Department addDepartment(Integer dept_id, String dept_title, String description) throws HRDeptBadRequestException;

    void updateDepartment(Integer dept_id, String dept_title, String dept_description) throws HRDeptBadRequestException;

    void deleteDepartment(Integer dept_id) throws HRDeptResourceNotFoundException;
}
