package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    void deleteById(Long doctorId);

    List<Doctor> findByCityAndSpeciality(String patientCity, Speciality s);
}
