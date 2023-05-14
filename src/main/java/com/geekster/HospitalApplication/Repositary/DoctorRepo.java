package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Doctor findByDoctorId(Long docId);

}
