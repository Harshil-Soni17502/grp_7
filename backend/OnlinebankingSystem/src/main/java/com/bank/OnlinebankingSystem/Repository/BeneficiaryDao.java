package com.bank.OnlinebankingSystem.Repository;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryDao extends JpaRepository<Beneficiary,Long> {

    //insert beneficiary
    
    //get beneficiaries of associated account
    public List<Beneficiary> findByAssociatedAccountNo(Long accountNo);

    public void deleteByAssociatedAccountNoAndBeneficiaryAccountNo(Long associatedAccountNo, Long beneficiaryAccountNo);

    //delete beneficiary
}
