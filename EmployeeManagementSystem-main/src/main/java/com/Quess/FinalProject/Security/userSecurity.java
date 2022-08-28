package com.Quess.FinalProject.Security;

import com.Quess.FinalProject.Repository.Employeerepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.Quess.FinalProject.Model.Employee;

import java.util.Optional;

@Component("userSecurity")
public class userSecurity {
    @Autowired
    Employeerepository userRepo;




    public boolean hasUserId(Authentication authentication, Integer userId) {
        Employee userID=userRepo.findById(userId).orElseThrow();
                if(authentication.getName()==userID.getEmail()){
                    return true;

                }
        return  false;
    }
}
