package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Repositary.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    public void addDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    public List<Doctor> getAllDocs() {
        List<Doctor> doctorList = doctorRepo.findAll();
        return doctorList;
    }

    public void removeDoctor(Long doctorId) {
        doctorRepo.deleteById(doctorId);
    }
}
