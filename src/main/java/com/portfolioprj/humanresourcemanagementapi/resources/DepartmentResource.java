package com.portfolioprj.humanresourcemanagementapi.resources;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.services.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {
    private DepartmentService departmentService;

    @Autowired
    public  DepartmentResource(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping(" ")
    public ResponseEntity<List<Department>> getAllDepartments(HttpServletRequest request){
        int authorizedUserId = (Integer) request.getAttribute("emplid");
        List<Department> departmentList = departmentService.fetchAllDepartments(authorizedUserId);
        System.out.println("list " + departmentList);
        return new ResponseEntity<>(departmentList, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(HttpServletRequest request,
                                                        @PathVariable("departmentId") Integer departmentId){
        int authorizedUserId = (Integer) request.getAttribute("emplid");
        Department department = departmentService.fetchDepartmentById(authorizedUserId, departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    // TO-DO - add authentication to only allow admin user to access these routes
    @PostMapping("/new-department")
    public ResponseEntity<Department> addDepartment(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> departmentMap){
        String title = (String) departmentMap.get("title");
        String desc = (String) departmentMap.get("description");
        int dept_head = (Integer) departmentMap.get("dept_head");
        int emplid = (Integer) request.getAttribute("emplid");
        Department department = departmentService.addDepartment(title, desc, dept_head, emplid);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }
}
