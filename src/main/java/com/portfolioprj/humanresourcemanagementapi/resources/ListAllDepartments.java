// * A workaround to return all departments since AuthFilter is affecting departments being returned

package com.portfolioprj.humanresourcemanagementapi.resources;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/api/all-departments")
public class ListAllDepartments {

    private DepartmentService departmentService;

    @Autowired
    public void DepartmentResource(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping(" ")
    public ResponseEntity<List<Department>> getAllDepartments(){
        //  int authorizedUserId = (Integer) request.getAttribute("emplid");
        List<Department> departmentList = departmentService.fetchAllDepartments();
        System.out.println("list " + departmentList);
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }
}
