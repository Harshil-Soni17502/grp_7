package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/beneficiary")
@RestController
public class BeneficiaryController {

    @Autowired
    BeneficiaryService beneficiaryService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertBeneficiary(@RequestBody Map<String,Object> payload){
    	return beneficiaryService.insertBeneficiary(
                Long.valueOf(payload.get("beneficiaryAccountNo").toString()),
                Long.valueOf(payload.get("associatedAccountNo").toString()),
                payload.get("beneficiaryName").toString());
    }

    //get beneficiaries of associated account

    @GetMapping("/get")
    public ResponseEntity<List<Beneficiary>> getBeneficiariesOf(@RequestBody Map<String,Object> payload){
        return beneficiaryService.getBeneficiariesOf(Long.valueOf(payload.get("associatedAccountNo").toString()));
    }

    //delete beneficiary

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBeneficiary(@RequestBody Map<String,Object> payload){
        return beneficiaryService.deleteBeneficiary(
               Long.valueOf(payload.get("beneficiaryAccountNo").toString()),
               Long.valueOf(payload.get("associatedAccountNo").toString())
        );
    }

}
