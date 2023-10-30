package com.portfolioprj.humanresourcemanagementapi.DAO;

public class Department {
    private Integer department_id;

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDept_head() {
        return dept_head;
    }

    public void setDept_head(Integer dept_head) {
        this.dept_head = dept_head;
    }

    public Integer getEmplid() {
        return emplid;
    }

    public void setEmplid(Integer emplid) {
        this.emplid = emplid;
    }

    public Department(Integer department_id, String title, String description, Integer dept_head, Integer emplid) {
        this.department_id = department_id;
        this.title = title;
        this.description = description;
        this.dept_head = dept_head;
        this.emplid = emplid;
    }

    private String title;
    private String description;
    private Integer dept_head;
    private Integer emplid; // the id of the employee with admin rights to access this & associated resources


}
