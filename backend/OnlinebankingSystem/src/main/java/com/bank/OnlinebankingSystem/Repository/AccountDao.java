package com.bank.OnlinebankingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<com.bank.OnlinebankingSystem.Entity.Account,Long> {

}
