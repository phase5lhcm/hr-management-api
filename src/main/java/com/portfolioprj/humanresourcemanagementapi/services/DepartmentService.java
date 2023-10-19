package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;

import java.util.List;

public interface DepartmentService {

    List<Department> fetchAllDepartments();

    Department fetchEmployeeDepartmentByEmplid(Integer emplid) throws HRDeptResourceNotFoundException;

    Department getDeptByDeptId(Integer deptId) throws HRDeptResourceNotFoundException;

    Department addDepartment(Integer dept_id, String dept_title, String description, Integer dept_head) throws HRDeptBadRequestException;

    void updateDepartment(Integer dept_id, String dept_title, String dept_description) throws HRDeptBadRequestException;

    void deleteDepartment(Integer dept_id) throws HRDeptResourceNotFoundException;
}
