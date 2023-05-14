package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.AuthenticationToken;
import com.geekster.HospitalApplication.Model.Patient;
import com.geekster.HospitalApplication.Repositary.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepo;

    public void saveToken(AuthenticationToken token){
        tokenRepo.save(token);
    }


    public AuthenticationToken getToken(Patient patient) {
        return tokenRepo.findByPatient(patient);
    }

    public boolean authenticate(String userEmail, String token) {
        AuthenticationToken authToken = tokenRepo.findFirstByToken(token);
        String expectedEmail = authToken.getPatient().getPatientEmail();
        return expectedEmail.equals(userEmail);
    }
}
