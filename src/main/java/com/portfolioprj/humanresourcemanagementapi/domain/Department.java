package com.portfolioprj.humanresourcemanagementapi.domain;

public class Department {
    public Department(int deptId, String title, String deptDesc, int deptHead) {
        this.department_id = deptId;
        this.dept_title = title;
        this.description = deptDesc;
        this.dept_head = deptHead;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
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

    public Integer getDept_head() {
        return dept_head;
    }

    public void setDept_head(Integer dept_head) {
        this.dept_head = dept_head;
    }

    private Integer dept_head;
    private String dept_title;
    private String description;
    private String head_of_dept; // not a db row, will be generated from emplid


}
