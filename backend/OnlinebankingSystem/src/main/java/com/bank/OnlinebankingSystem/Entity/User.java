package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private String salutation;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String emailId;
    @NotNull
    private String password;
    @NotNull
    @Pattern(regexp="\\d{10}")
    private String mobileNumber;
    @NotNull
    @Pattern(regexp="\\d{12}")
    private String aadharNumber;
    @NotNull
    private String permanentAddress;
    @NotNull
    private String residentialAddress;
    @NotNull
    private String dob;
    @NotNull
    private String occupation;


    public User() {
    }

    public User(long id, String salutation,
                String firstName, String lastName, String emailId,
                String password, String mobileNumber, String aadharNumber,
                String permanentAddress, String residentialAddress,
                String dob, String occupation) {
        this.id = id;

        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.aadharNumber = aadharNumber;
        this.permanentAddress = permanentAddress;
        this.residentialAddress = residentialAddress;
        this.dob = dob;
        this.occupation = occupation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "salutation", nullable = false, unique=false, insertable=true, updatable=true)
    public String getSalutation() {
        return salutation;
    }
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Column(name = "first_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false, unique=true, insertable=true, updatable=true)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    @Column(name = "password", nullable = false, unique=false, insertable=true, updatable=true)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name = "mobile_number", nullable = false, unique=true, insertable=true, updatable=true)
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    @Column(name = "aadhar_number", nullable = false, unique=true, insertable=true, updatable=true)
    public String getAadharNumber() {
        return aadharNumber;
    }
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
    
    @Column(name = "residential_address", nullable = false, unique=false, insertable=true, updatable=true)
    public String getResidentialAddress() {
    	return residentialAddress;
    }
    public void setResidentialAddress(String residentialAddress) {
    	this.residentialAddress = residentialAddress;
    }
    
    @Column(name = "permanent_address", nullable = false, unique=false, insertable=true, updatable=true)
    public String getPermanentAddress() {
    	return permanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
    	this.permanentAddress = permanentAddress;
    }
    
    @Column(name = "dob", nullable = false, unique=false, insertable=true, updatable=true)
    public String getDOB() {
    	return dob;
    }
    public void setDOB(String dob) {
    	this.dob = dob;
    }
    
    @Column(name = "occupation", nullable = false, unique=false, insertable=true, updatable=true)
    public String getOccupation() {
    	return occupation;
    }
    public void setOccupation(String occupation) {
    	this.occupation = occupation;
    }
    
    
}
