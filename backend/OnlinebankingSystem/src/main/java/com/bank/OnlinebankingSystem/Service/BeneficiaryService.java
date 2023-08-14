package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryDao beneficiaryDao;
    @Autowired
    AccountDao accountDao;

    //insert beneficiary
    public ResponseEntity<String> insertBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo, String beneficiaryName ){
        try{
            Beneficiary beneficiary = new Beneficiary();
            
            beneficiary.setBeneficiaryName(beneficiaryName);
            Optional<Account> associatedAccount = accountDao.findById(associatedAccountNo);
            Optional<Account> beneficiaryAccount = accountDao.findById(beneficiaryAccountNo);
            beneficiary.setAssociatedAccount(associatedAccount.get());
            beneficiary.setBeneficiaryAccount(beneficiaryAccount.get());
            beneficiaryDao.save(new Beneficiary());
            return ResponseEntity.ok("OK");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    //get beneficiaries of associated account

    public ResponseEntity<List<Beneficiary>> getBeneficiariesOf(Long accountNo){
        try {
        	Optional<Account> account = accountDao.findById(accountNo);
            return ResponseEntity.ok().body(
                    beneficiaryDao.findByAssociatedAccount(account.get()));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    //delete beneficiary

    public ResponseEntity<String> deleteBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo){
        try{
        	Optional<Account> associatedAccount = accountDao.findById(associatedAccountNo);
        	Optional<Account> beneficiaryAccount = accountDao.findById(beneficiaryAccountNo);
            beneficiaryDao.deleteByAssociatedAccountAndBeneficiaryAccount(associatedAccount.get(),beneficiaryAccount.get());
            return ResponseEntity.ok().body(
                    "OK"
            );
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


}
