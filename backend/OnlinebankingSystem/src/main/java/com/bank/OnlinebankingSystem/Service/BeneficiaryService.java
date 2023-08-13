package com.bank.OnlinebankingSystem.Service;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Repository.BeneficiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryDao beneficiaryDao;

    //insert beneficiary
    public ResponseEntity<String> insertBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo, String beneficiaryName ){
        try{
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setBeneficiaryAccountNo(beneficiaryAccountNo);
            beneficiary.setAssociatedAccountNo(associatedAccountNo);
            beneficiary.setBeneficiaryName(beneficiaryName);
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
            return ResponseEntity.ok().body(
                    beneficiaryDao.findByAssociatedAccountNo(accountNo)
            );
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    //delete beneficiary

    public ResponseEntity<String> deleteBeneficiary(Long beneficiaryAccountNo, Long associatedAccountNo){
        try{
            beneficiaryDao.deleteByAssociatedAccountNoAndBeneficiaryAccountNo(associatedAccountNo,beneficiaryAccountNo);
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
