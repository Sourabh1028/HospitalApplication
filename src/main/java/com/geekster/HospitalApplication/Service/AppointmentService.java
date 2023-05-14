package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.Appointment;
import com.geekster.HospitalApplication.Model.AppointmentKey;
import com.geekster.HospitalApplication.Repositary.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;


    public void bookAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    public void cancelAppointment(AppointmentKey key) {
        appointmentRepo.deleteById(key);
    }
}
