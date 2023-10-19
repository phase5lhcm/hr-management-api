package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.domain.Department;
import com.portfolioprj.humanresourcemanagementapi.domain.Employee;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptBadRequestException;
import com.portfolioprj.humanresourcemanagementapi.helpers.exceptions.HRDeptResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public Department findDeptById(Integer dept_id) throws HRDeptResourceNotFoundException {
        try {
           return jdbcTemplate.queryForObject(QUERIES.SQL_FIND_DEPT_BY_ID, departmentRowMapper, dept_id);
        } catch (Exception e) {
            throw new HRDeptResourceNotFoundException("No department was found with that id");
        }
    }

    @Override
    public Department findDeptByEmplid(Integer emplid) throws HRDeptResourceNotFoundException {
        return null;
    }

    @Override
    public Integer createDept(Integer dept_id, String dept_title, String description, Integer dept_head) throws HRDeptBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(QUERIES.SQL_DEPT_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, dept_id);
                ps.setString(2, dept_title);
                ps.setString(3, description);
                ps.setInt(4, dept_head);
                return ps;
            }, keyHolder);
            return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("DEPT_ID");
        } catch (Exception e) {
            throw new HRDeptBadRequestException("Invalid Request");
        }
    }

    @Override
    public void updateDeptInfo(Integer dept_id, Department department) throws HRDeptBadRequestException {

    }

    @Override
    public void removeDeptById(Integer deptId) {

    }

    private final RowMapper<Department> departmentRowMapper= ((rs, rowNum) -> {
        return new Department(
                rs.getInt("DEPT_ID"),
                rs.getString("TITLE"),
                rs.getString("DEPT_DESC"),
                rs.getInt("DEPT_HEAD"));
    });
}
