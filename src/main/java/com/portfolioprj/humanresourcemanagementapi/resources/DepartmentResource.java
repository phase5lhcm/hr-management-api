package com.portfolioprj.humanresourcemanagementapi.resources;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.services.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {
    private DepartmentService departmentService;

    public  DepartmentResource(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping(" ")
    public String getAllDepartments(HttpServletRequest request){
        Integer emplID = (Integer) request.getAttribute("emplid");
        if(emplID != null){
            return "Authentication success. Employee id: " + emplID;
        } else{
            throw new RuntimeException("An authorized user must be logged in to access this resource");
        }
    }

    // TO-DO - add authentication to only allow admin user to access these routes
    @PostMapping("/new-department")
    public ResponseEntity<Department> addDepartment(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> departmentMap){
        int dept_id = (Integer) request.getAttribute("dept_id");
        String title = (String) departmentMap.get("title");
        String desc = (String) departmentMap.get("dept_desc");
        int dept_head = (Integer) departmentMap.get("dept_head");
        Department department = departmentService.addDepartment(dept_id, title, desc, dept_head);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }
}
