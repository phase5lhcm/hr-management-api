package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;

import java.util.List;

public interface DepartmentService {

    List<Department> fetchAllDepartments();

    Department fetchDepartmentById(Integer emplid, Integer department_id) throws HRDeptResourceNotFoundException;

    Department addDepartment(String title, String description, Integer dept_head, Integer userId) throws HRDeptBadRequestException;

    void updateDepartment(Integer emplid, Integer dept_id, Department department) throws HRDeptBadRequestException;

    void deleteDepartment(Integer userId, Integer dept_id) throws HRDeptResourceNotFoundException;
}
