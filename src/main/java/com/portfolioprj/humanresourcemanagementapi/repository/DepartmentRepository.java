package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;

import java.util.List;


public interface DepartmentRepository {

    List<Department> findAllDepartments() throws HRDeptResourceNotFoundException;

    Department findDeptById(Integer emplid, Integer department_id) throws HRDeptResourceNotFoundException;

    Department findDeptByDepartmentManager(Integer emplid, Integer dept_head) throws HRDeptResourceNotFoundException;

    Integer createDept(String title, String description, Integer dept_head, Integer userId) throws HRDeptBadRequestException;

    void updateDeptInfo(Integer emplid, Integer department_id, Department department) throws HRDeptBadRequestException;

    void removeDeptById(Integer userId, Integer department_id) throws HRDeptResourceNotFoundException;
}
