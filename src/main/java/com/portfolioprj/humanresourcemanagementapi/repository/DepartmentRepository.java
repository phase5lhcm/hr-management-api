package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface DepartmentRepository {

    List<Department> findAllDepartments() throws HRDeptResourceNotFoundException;

    Department findDeptById(Integer dept_id) throws HRDeptResourceNotFoundException;

    Department findDeptByEmplid(Integer emplid) throws HRDeptResourceNotFoundException;

    Integer createDept(Integer dept_id, String dept_title, String description, Integer dept_head) throws HRDeptBadRequestException;

    void updateDeptInfo(Integer dept_id, Department department) throws HRDeptBadRequestException;

    void removeDeptById(Integer dept_id) throws HRDeptResourceNotFoundException;
}
