package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Model.Patient;
import com.geekster.HospitalApplication.Repositary.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorService doctorService;


    public List<Doctor> getAllDocs() {
        return doctorService.getAllDocs();
    }

    public void addPatient(Patient patient) {
        patientRepo.save(patient);
    }

    public void removePatient(Integer patientId) {
        patientRepo.deleteById(patientId);
    }
}
