package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;

import java.util.List;

public interface DepartmentService {

    List<Department> fetchAllDepartments(Integer emplid);

    Department fetchDepartmentById(Integer userId, Integer department_id) throws HRDeptResourceNotFoundException;

    Department addDepartment(String title, String description, Integer dept_head, Integer userId) throws HRDeptBadRequestException;

    void updateDepartment(Integer userId, Integer dept_id, Department department) throws HRDeptBadRequestException;

    void deleteDepartment(Integer userId, Integer dept_id) throws HRDeptResourceNotFoundException;
}
