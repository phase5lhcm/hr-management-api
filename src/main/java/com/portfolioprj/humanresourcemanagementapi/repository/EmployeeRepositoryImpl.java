package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.exceptions.HRAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String SQL_CREATE_EMPL = "INSERT INTO EMPLOYEE(EMPLID, FIRST_NAME, LAST_NAME,ADDRESS, EMAIL, PASSWORD) VALUES(NEXTVAL('EMPLOYEE_SEQ'), ?, ?, ?, ?, ?)";
    private static final String SQL_EMAIL_COUNT = "SELECT COUNT(*) FROM EMPLOYEE WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT EMPLID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PASSWORD FROM EMPLOYEE WHERE EMPLID = ?";

    private static final String SQL_FIND_EMPL_BY_EMAIL  = "SELECT EMPLID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PASSWORD FROM EMPLOYEE WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer createEmployee(String firstName, String lastName, String address, String email, String password) throws HRAuthException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_CREATE_EMPL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, address);
                ps.setString(4, email);
                ps.setString(5, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("EMPLID");
        } catch (Exception e){
            throw new HRAuthException("Invalid details. Failed to create account for " + email);
        }
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) throws HRAuthException {
        try{
            // using varags method to get results since queryForObject is deprecated in Spring jdbc > 2.4X
            Employee employee = jdbcTemplate.queryForObject(SQL_FIND_EMPL_BY_EMAIL, employeeRowMapper, email);
            assert employee != null;
            if(!password.equals(employee.getPassword()))
                throw new HRAuthException("Invalid login credentials, please check your password");
            return employee;
        } catch (DataAccessException e){
            throw new HRAuthException("Invalid login credentials");

        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_EMAIL_COUNT, Integer.class, email);
    }

    @Override
    public Employee findById(Integer emplid) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, employeeRowMapper, emplid);
    }

    private final RowMapper<Employee> employeeRowMapper = ((rs, rowNum) -> {
        return new Employee(rs.getInt("EMPLID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("ADDRESS"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });
}
