package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, Long> {

    Patient findFirstByPatientEmail(String userEmail);
}
