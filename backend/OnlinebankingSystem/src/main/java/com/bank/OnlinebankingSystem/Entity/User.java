package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.DecimalMin;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
    private long id;
    private String salutation;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private String mobileNumber;
    private String aadharNumber;
    private String permanentAddress;
    private String residentialAddress;
    private String dob;
    private String occupation;
    private Double totalGrossIncome;
//    private Boolean approved;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", salutation='" + salutation + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", residentialAddress='" + residentialAddress + '\'' +
                ", dob='" + dob + '\'' +
                ", occupation='" + occupation + '\'' +
                ", totalGrossIncome=" + totalGrossIncome +
                //", approved=" + "approved" +
                '}';
    }

    public User() {
    }

    public User(long id, String salutation,
                String firstName, String lastName, String emailId,
                String password, String mobileNumber, String aadharNumber,
                String permanentAddress, String residentialAddress,
                String dob, String occupation, Double totalGrossIncome) {
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
        this.totalGrossIncome = totalGrossIncome;
    }
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable=false, unique = true)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @NotNull
    @Column(name = "salutation", nullable = false, unique=false, insertable=true, updatable=true)
    public String getSalutation() {
        return salutation;
    }
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
    
    @NotNull
    @Column(name = "first_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @NotNull
    @Column(name = "last_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @NotNull
    @Column(name = "email_address", nullable = false, unique=true, insertable=true, updatable=true)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    @NotNull
    @Column(name = "password", nullable = false, unique=false, insertable=true, updatable=true)
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    @NotNull
    @Pattern(regexp="\\d{10}")
    @Column(name = "mobile_number", nullable = false, unique=true, insertable=true, updatable=true)
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    @NotNull
    @Pattern(regexp="\\d{12}")
    @Column(name = "aadhar_number", nullable = false, unique=true, insertable=true, updatable=true)
    public String getAadharNumber() {
        return aadharNumber;
    }
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
    
    @NotNull
    @Column(name = "residential_address", nullable = false, unique=false, insertable=true, updatable=true)
    public String getResidentialAddress() {
    	return residentialAddress;
    }
    public void setResidentialAddress(String residentialAddress) {
    	this.residentialAddress = residentialAddress;
    }
    
    @NotNull
    @Column(name = "permanent_address", nullable = false, unique=false, insertable=true, updatable=true)
    public String getPermanentAddress() {
    	return permanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
    	this.permanentAddress = permanentAddress;
    }
    
    @NotNull
    @Column(name = "dob", nullable = false, unique=false, insertable=true, updatable=true)
    public String getDOB() {
    	return dob;
    }
    public void setDOB(String dob) {
    	this.dob = dob;
    }
    
    @NotNull
    @Column(name = "occupation", nullable = false, unique=false, insertable=true, updatable=true)
    public String getOccupation() {
    	return occupation;
    }
    public void setOccupation(String occupation) {
    	this.occupation = occupation;
    }
    
    @NotNull
    @DecimalMin("0.00")
    @Column(name = "total_gross_income", nullable = false, unique=false, insertable=true, updatable=true)
	public Double getTotalGrossIncome() {
		return totalGrossIncome;
	}

	public void setTotalGrossIncome(Double totalGrossIncome) {
		this.totalGrossIncome = totalGrossIncome;
	}
	
    
}
