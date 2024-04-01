package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;


@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Department> findAllDepartments() throws HRDeptResourceNotFoundException {
        return jdbcTemplate.query(QUERIES.SQL_FIND_ALL_DEPARTMENTS, departmentRowMapper);
    }

    @Override
    public Department findDeptById(Integer emplid, Integer dept_id) throws HRDeptResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(QUERIES.SQL_FIND_DEPT_BY_ID, departmentRowMapper, emplid, dept_id);
        } catch (EmptyResultDataAccessException e) {
            // For now, only matching dept heads can access their department info
            throw new HRDeptResourceNotFoundException("You are unauthorized to access this resource");
        } catch (DataAccessException e) {
            // Handle database access errors
            throw new HRDeptResourceNotFoundException("Error accessing database");
        } catch (Exception e) {
            // Handle other unexpected exceptions
            throw new HRDeptResourceNotFoundException("An unexpected error occurred");
        }
    }

    @Override
    public Department findDeptByDepartmentManager(Integer emplid, Integer dept_head) throws HRDeptResourceNotFoundException {
        return null;
    }

    @Override
    public Integer createDept(String title, String description, Integer dept_head, Integer emplid) throws HRDeptBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    con -> {
                        PreparedStatement ps = con.prepareStatement(QUERIES.SQL_DEPT_CREATE, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, title);
                        ps.setString(2, description);
                        ps.setInt(3, dept_head);
                        ps.setInt(4, emplid);
                        return ps;
                    }, keyHolder);
          return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("DEPARTMENT_ID");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new HRDeptBadRequestException("Invalid request, you may not be authorized to access this resource or you have invalid form fields");
        }
    }

    @Override
    public void updateDeptInfo(Integer emplid, Integer dept_id, Department department) throws HRDeptBadRequestException {
        try{
            jdbcTemplate.update(QUERIES.SQL_UPDATE_DEPARTMENT,
                    department.getTitle(), department.getDescription(),emplid, dept_id);

        } catch (DataAccessException e){
            throw new HRDeptBadRequestException(e.getMessage());
        }

    }

    @Override
    public void deleteDeptById(Integer emplid, Integer dept_id) throws HRDeptResourceNotFoundException {
        try{
            int rowsAffected = jdbcTemplate.update(QUERIES.SQL_DELETE_DEPARTMENT, emplid, dept_id);
            if (rowsAffected == 0) {
                throw new HRDeptResourceNotFoundException("Department with id " + dept_id + " not found for employee with id " + emplid);
            }
        } catch (DataAccessException e) {
            throw new HRDeptBadRequestException("Error occurred while deleting department: " + e.getMessage());
        }

    }

    private final RowMapper<Department> departmentRowMapper= ((rs, rowNum) -> {
        return new Department(
                rs.getInt("DEPARTMENT_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getInt("DEPT_HEAD"),
                rs.getInt("EMPLID")
        );
    });
}
