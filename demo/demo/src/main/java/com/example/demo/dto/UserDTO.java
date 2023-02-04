package com.example.demo.dto;

import java.util.Date;

public class UserDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private Date createdDate;
    private Date updatedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UserDTO(Long id, String firstName, String lastName, String phoneNo, String email, Date createdDate, Date updatedDate) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public UserDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    

}
