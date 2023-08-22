package com.bank.OnlinebankingSystem.Repository;

import com.bank.OnlinebankingSystem.Entity.Account;
import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account,Long> {

    // update balance in account
    public List<Account> findByUser_Id(Long id);
    public List<Account> findByIsApprovedTrueAndUser_Id(Long id);
    public List<Account> findAllByIsApproved(Boolean status);
    Optional<Account> findById(Long id);
}
