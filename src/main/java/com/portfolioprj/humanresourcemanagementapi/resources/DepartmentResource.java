package com.portfolioprj.humanresourcemanagementapi.resources;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;
import com.portfolioprj.humanresourcemanagementapi.services.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
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

  @PutMapping("update/{departmentId}")
   public  ResponseEntity<Map<String, Boolean>> updateDepartment(HttpServletRequest request,
                                                              @PathVariable("departmentId") Integer departmentId,
                                                              @RequestBody Department department){
        int emplid = (Integer) request.getAttribute("emplid");
        departmentService.updateDepartment(emplid, departmentId, department);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("delete/{departmentId}")
    public ResponseEntity<Map<String, Boolean>> deleteDepartment(HttpServletRequest request,
                                                                 @PathVariable("departmentId") Integer departmentId) {
        try {
            Integer emplid = (Integer) request.getAttribute("emplid");
            if (emplid == null) {
                throw new HRDeptBadRequestException("Employee ID not found in the request");
            }

            departmentService.deleteDepartment(emplid, departmentId);
            Map<String, Boolean> map = Collections.singletonMap("success", true);
            return ResponseEntity.ok(map);
        } catch (HRDeptResourceNotFoundException e) {
            // Handle resource not found exception
            Map<String, Boolean> map = Collections.singletonMap("success", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        } catch (HRDeptBadRequestException e) {
            // Handle bad request exception
            Map<String, Boolean> map = Collections.singletonMap("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            Map<String, Boolean> map = Collections.singletonMap("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
