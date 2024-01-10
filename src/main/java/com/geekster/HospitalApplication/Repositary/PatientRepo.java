package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepo extends CrudRepository<Patient, Integer> {

    void deleteById(Integer patientId);

}
