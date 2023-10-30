package com.portfolioprj.humanresourcemanagementapi.services;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
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
    public List<Department> fetchAllDepartments(Integer emplid) {
        return null;
    }

    @Override
    public Department fetchDepartmentById(Integer emplid, Integer department_id) throws HRDeptResourceNotFoundException {
        return departmentRepository.findDeptById(emplid, department_id);
    }

    @Override
    public Department addDepartment(String title, String description, Integer dept_head, Integer emplid) throws HRDeptBadRequestException {
        int departmentId = departmentRepository.createDept(title, description, dept_head, emplid);
        return departmentRepository.findDeptById(emplid, departmentId);
    }

    @Override
    public void updateDepartment(Integer emplid, Integer dept_id, Department department) throws HRDeptBadRequestException {

    }

    @Override
    public void deleteDepartment(Integer emplid, Integer dept_id) throws HRDeptResourceNotFoundException {

    }
}
