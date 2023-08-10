package com.bank.OnlinebankingSystem.Entity;


import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String salutation;
    @javax.validation.constraints.NotNull
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

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
