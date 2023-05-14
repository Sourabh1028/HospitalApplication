package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.AuthenticationToken;
import com.geekster.HospitalApplication.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<AuthenticationToken, Long> {


    AuthenticationToken findByPatient(Patient patient);

    AuthenticationToken findFirstByToken(String token);

}
