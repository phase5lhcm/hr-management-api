package com.portfolioprj.humanresourcemanagementapi.DAO;

public class Employee {
    private Integer emplid;
    private String firstName;

    public Employee(Integer emplid, String firstName, String lastName, String address, String email, String password) {
        this.emplid = emplid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public Integer getEmplid() {
        return emplid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmplid(Integer emplid) {
        this.emplid = emplid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String lastName;

    private String address;

    private String email;
    private String password;
}
