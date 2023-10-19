package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;
import com.portfolioprj.humanresourcemanagementapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
    // use constructor dependency injection instead of field injection
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> fetchAllDepartments() {
        return null;
    }

    @Override
    public Department fetchEmployeeDepartmentByEmplid(Integer emplid) throws HRDeptResourceNotFoundException {
        return null;
    }

    @Override
    public Department getDeptByDeptId(Integer deptId) throws HRDeptResourceNotFoundException {
        return null;
    }

    @Override
    public Department addDepartment(Integer dept_id, String dept_title, String description, Integer dept_head) throws HRDeptBadRequestException {
        int deptId = departmentRepository.createDept(dept_id, dept_title, description, dept_head);
        return departmentRepository.findDeptById(deptId);
    }

    @Override
    public void updateDepartment(Integer dept_id, String dept_title, String dept_description) throws HRDeptBadRequestException {

    }

    @Override
    public void deleteDepartment(Integer dept_id) throws HRDeptResourceNotFoundException {

    }
}
