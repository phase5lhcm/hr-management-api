package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;

import java.util.List;

public interface DepartmentRepository {

    List<Department> findAllDepartments() throws HRDeptResourceNotFoundException;

    Department findDeptByEmplid(Integer emplid) throws HRDeptResourceNotFoundException;

    Integer createDept(Integer dept_id, String dept_title, String description) throws HRDeptBadRequestException;

    void updateDeptInfo(Integer dept_id, Department department) throws HRDeptBadRequestException;

    void removeDeptById(Integer deptId);
