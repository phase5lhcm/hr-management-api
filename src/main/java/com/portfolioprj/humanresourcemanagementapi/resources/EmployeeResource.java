package com.portfolioprj.humanresourcemanagementapi.resources;

import com.portfolioprj.humanresourcemanagementapi.CONSTANTS;
import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.services.EmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> employeeLogin(@RequestBody Map<String, Object> employeeMap){
        String email = (String) employeeMap.get("email");
        String password = (String) employeeMap.get("password");
        Employee employee = employeeService.validateEmployee(email, password);
        return new ResponseEntity<>(generateJWT(employee), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerEmployee(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String address = (String) userMap.get("address");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        Employee employee = employeeService.registerEmployee(firstName, lastName, address, email, password);
        return new ResponseEntity<>(generateJWT(employee), HttpStatus.OK);
    }

    // generate a jwt token
    private Map<String, String> generateJWT(Employee employee){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(
                SignatureAlgorithm.HS256, CONSTANTS.API_STRING_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + CONSTANTS.TOKEN_VALIDITY_LENGTH))
                .claim("emplid", employee.getEmplid())
                .claim("email", employee.getEmail())
                .claim("first name", employee.getFirstName())
                .claim("last name", employee.getLastName())
                .compact();
        Map<String, String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put("token", token);
        return jwtTokenMap;

    }


}
