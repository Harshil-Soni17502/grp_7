package com.bank.OnlinebankingSystem.Repository;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.OnlinebankingSystem.Entity.Account;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface BeneficiaryDao extends JpaRepository<Beneficiary,Long> {


    //insert beneficiary
    
    //get beneficiaries of associated account 
	public List<Beneficiary> findByAssociatedAccount_Id(Long id);

    public void deleteByAssociatedAccount_IdAndBeneficiaryAccount_Id(Long associatedAccountId, Long beneficiaryAccountId);

    //delete beneficiary
}
