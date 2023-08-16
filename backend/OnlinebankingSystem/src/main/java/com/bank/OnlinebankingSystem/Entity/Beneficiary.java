package com.bank.OnlinebankingSystem.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Beneficiary", uniqueConstraints= {@UniqueConstraint(name="UniqueAccounts", columnNames= {"associated_account_no", "beneficiary_account_no"})})
public class Beneficiary {

    Long id;
    Account beneficiaryAccount;
    String beneficiaryName;
    Account associatedAccount;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull
    @Column(name = "beneficiary_name", nullable = false, unique=false, insertable=true, updatable=true)
    public String getBeneficiaryName() {
        return beneficiaryName;
    }
    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }
    
    @ManyToOne
    @JoinColumn(name = "associated_account_no", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Account getAssociatedAccount() {
        return associatedAccount;
    }

    public void setAssociatedAccount(Account associatedAccount) {
        this.associatedAccount = associatedAccount;
    }
    
    @ManyToOne
    @JoinColumn(name = "beneficiary_account_no", referencedColumnName = "id", nullable = false)
    @JsonIncludeProperties({"id"})
    public Account getBeneficiaryAccount() {
        return this.beneficiaryAccount;
    }

    public void setBeneficiaryAccount(Account beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }
}
