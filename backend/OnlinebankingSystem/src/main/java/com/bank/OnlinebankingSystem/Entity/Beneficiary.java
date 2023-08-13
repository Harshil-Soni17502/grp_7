package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;


    @NotNull
    Long beneficiaryAccountNo;

    @NotNull
    String beneficiaryName;

    @NotNull
    Long associatedAccountNo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "beneficiary_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    @Column(name = "associated_account_no", nullable = false, unique=false, insertable=true, updatable=true)
    public Long getAssociatedAccountNo() {
        return associatedAccountNo;
    }

    public void setAssociatedAccountNo(Long associatedAccountNo) {
        this.associatedAccountNo = associatedAccountNo;
    }

    @Column(name = "beneficiary_account_no", nullable = false, unique=false, insertable=true, updatable=true)
    public Long getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(Long beneficiaryAccount) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }
}
