package com.portfolioprj.humanresourcemanagementapi.domain;

public class Department {
    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Integer getEmplid() {
        return emplid;
    }

    public void setEmplid(Integer emplid) {
        this.emplid = emplid;
    }

    public String getDept_title() {
        return dept_title;
    }

    public void setDept_title(String dept_title) {
        this.dept_title = dept_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHead_of_dept() {
        return head_of_dept;
    }

    public void setHead_of_dept(String head_of_dept) {
        this.head_of_dept = head_of_dept;
    }

    private Integer department_id;
    private Integer emplid;
    private String dept_title;
    private String description;
    private String head_of_dept; // not a db row, will be generated from emplid


}
