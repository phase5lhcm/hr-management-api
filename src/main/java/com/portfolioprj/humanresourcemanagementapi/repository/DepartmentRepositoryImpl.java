package com.portfolioprj.humanresourcemanagementapi.repository;

import com.portfolioprj.humanresourcemanagementapi.DAO.Department;
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
    public List<Department> findAllDepartments(Integer emplid) throws HRDeptResourceNotFoundException {
        return null;
    }

    @Override
    public Department findDeptById(Integer emplid, Integer dept_id) throws HRDeptResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(QUERIES.SQL_FIND_DEPT_BY_ID, departmentRowMapper, emplid, dept_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HRDeptResourceNotFoundException("Dept not found with this id");
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

    }

    @Override
    public void removeDeptById(Integer emplid, Integer dept_id) throws HRDeptResourceNotFoundException {

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
