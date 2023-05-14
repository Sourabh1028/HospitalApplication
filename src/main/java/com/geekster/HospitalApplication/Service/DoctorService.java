package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.Appointment;
import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Repositary.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    public void addDoctors(Doctor doctor) {
        doctorRepo.save(doctor);
    }

    public List<Doctor> getAllDocs() {
        List<Doctor> doctorList = doctorRepo.findAll();
        return doctorList;
    }

    public List<Appointment> getMyAppointment(Long docId) {
        Doctor myDoc = doctorRepo.findByDoctorId(docId);
        if(myDoc == null){
            throw  new IllegalStateException("The doctor does not exist...");
        }
        return myDoc.getAppointments();
    }
}
