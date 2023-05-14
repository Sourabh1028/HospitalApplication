package com.geekster.HospitalApplication.Repositary;

import com.geekster.HospitalApplication.Model.Appointment;
import com.geekster.HospitalApplication.Model.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, AppointmentKey> {

    public String findByIdAppId(Long id);
}
