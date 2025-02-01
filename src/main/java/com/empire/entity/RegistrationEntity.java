package com.empire.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Employee_Registration")
public class RegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_Id")
    private Long empId;

    @Column(name = "Employee_Name")
    private String name;

    @Column(name = "Employee_Email", unique = true)
    private String email;

    @Column(name = "Employee_Mobile")
    private Long mobile;

    @Column(name = "Date_Of_Joining")
    private LocalDate dateOfJoining;

    @Column(name = "Date_Of_Birth")
    private LocalDate dob;

    @Column(name = "Password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "role")
    private String role;

    public RegistrationEntity() {
        this.role = "USER"; // Default role for every new user
    }

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getMobile() {
        return mobile;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "RegistrationEntity{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", dateOfJoining=" + dateOfJoining +
                ", dob=" + dob +
                ", role='" + role + '\'' +
                '}';
    }
}
