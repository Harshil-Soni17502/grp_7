package com.bank.OnlinebankingSystem.Service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {



    public boolean loginUser(String email, String password) {

        if(email.equals("admin2@gmail.com") && password.equals("pass2")){
            return true;
        }

        return false;

    }
}
