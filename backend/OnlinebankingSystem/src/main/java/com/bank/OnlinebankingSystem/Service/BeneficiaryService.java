package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;
import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.Repository.AccountDao;
import com.bank.OnlinebankingSystem.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryDao beneficiaryDao;
    @Autowired
    AccountDao accountDao;

    //insert beneficiary
    @Transactional
    public ResponseEntity<String> insertBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo, String beneficiaryName )throws MalformedRequestException, EntityExistsException, Exception{
    	Beneficiary beneficiary = new Beneficiary();
    	try{
            beneficiary.setBeneficiaryName(beneficiaryName);
            Optional<Account> associatedAccount = accountDao.findById(associatedAccountNo);
            Optional<Account> beneficiaryAccount = accountDao.findById(beneficiaryAccountNo);
            beneficiary.setAssociatedAccount(associatedAccount.get());
            beneficiary.setBeneficiaryAccount(beneficiaryAccount.get());
        }
        catch(NoSuchElementException e) {
        	throw new MalformedRequestException("Account no does not exist");
        }
        catch(Exception e) {
        	throw new MalformedRequestException("insert beneficiary request bad");
        }
        try {
            beneficiaryDao.save(beneficiary);
            return ResponseEntity.ok("OK");
        }
        catch(DataIntegrityViolationException e) {
        	throw new EntityExistsException("Benficiary already exists!");
        }
        catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }
    }

    //get beneficiaries of associated account
    @Transactional
    public ResponseEntity<List<Beneficiary>> getBeneficiariesOf(Long accountNo)throws Exception{
        try {
        	//Optional<Account> account = accountDao.findById(accountNo);
            return ResponseEntity.ok().body(beneficiaryDao.findByAssociatedAccount_Id(accountNo));
        }
        catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }
    }

    //delete beneficiary
    @Transactional
    public ResponseEntity<String> deleteBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo)throws Exception{
        try{
        	//Optional<Account> associatedAccount = accountDao.findById(associatedAccountNo);
        	//Optional<Account> beneficiaryAccount = accountDao.findById(beneficiaryAccountNo);
            beneficiaryDao.deleteByAssociatedAccount_IdAndBeneficiaryAccount_Id(associatedAccountNo,beneficiaryAccountNo);
            return ResponseEntity.ok().body(
                    "OK"
            );
        }
        catch (Exception e){
        	throw new Exception("Server error: "+e.getMessage());
        }
    }


}
