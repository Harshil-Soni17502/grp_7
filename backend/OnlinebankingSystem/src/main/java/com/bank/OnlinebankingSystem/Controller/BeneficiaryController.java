package com.bank.OnlinebankingSystem.Controller;

import com.bank.OnlinebankingSystem.Entity.Beneficiary;
import com.bank.OnlinebankingSystem.Service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.OnlinebankingSystem.exception.MalformedRequestException;
import com.bank.OnlinebankingSystem.exception.EntityExistsException;

import java.util.List;
import java.util.Map;

@RequestMapping("/beneficiary")
@RestController
public class BeneficiaryController {

    @Autowired
    BeneficiaryService beneficiaryService;

    @PostMapping("/insert")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> insertBeneficiary(@RequestBody Map<String,Object> payload)throws MalformedRequestException, EntityExistsException, Exception{
    	return beneficiaryService.insertBeneficiary(
                Long.valueOf(payload.get("beneficiaryAccountNo").toString()),
                Long.valueOf(payload.get("associatedAccountNo").toString()),
                payload.get("beneficiaryName").toString());
    }

    //get beneficiaries of associated account

    @GetMapping("/get")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<List<Beneficiary>> getBeneficiariesOf(@RequestParam String associatedAccountNo)throws MalformedRequestException, Exception{
        return beneficiaryService.getBeneficiariesOf(Long.valueOf(associatedAccountNo));
    }

    //delete beneficiary

    @DeleteMapping("/delete")
    @CrossOrigin(origins ="http://localhost:3000")
    public ResponseEntity<String> deleteBeneficiary(@RequestBody Map<String,Object> payload)throws Exception{
        return beneficiaryService.deleteBeneficiary(
               Long.valueOf(payload.get("beneficiaryAccountNo").toString()),
               Long.valueOf(payload.get("associatedAccountNo").toString())
        );
    }

}
