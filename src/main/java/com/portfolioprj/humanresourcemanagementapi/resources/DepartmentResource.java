package com.portfolioprj.humanresourcemanagementapi.resources;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class DepartmentResource {
    @GetMapping(" ")
    public String getAllDepartments(HttpServletRequest request){
        Integer emplID = (Integer) request.getAttribute("emplid");
        if(emplID != null){
            return "Authentication success. Employee id: " + emplID;
        } else{
            throw new RuntimeException("An authorized user must be logged in to access this resource");
        }
    }
}
