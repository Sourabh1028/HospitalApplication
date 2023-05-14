package com.geekster.HospitalApplication.Controller;

import com.geekster.HospitalApplication.Model.Appointment;
import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/")
    public void addDoctors(@RequestBody Doctor doctor)
    {
         doctorService.addDoctors(doctor);
    }

    @GetMapping("{docId}/appointment")
    ResponseEntity<List<Appointment>> getDocMyAppointment(@PathVariable Long docId){
        List<Appointment> myAppointment = null;
        HttpStatus status;
        try{
             myAppointment = doctorService.getMyAppointment(docId);
             //System.out.println(myAppointment);
             if(myAppointment.isEmpty()){
                 status=HttpStatus.NO_CONTENT;
             }
             else{
                 status=HttpStatus.OK;
             }
        }
        catch(Exception e){
            System.out.println("Doc Id is Not Valid...!!!");
            status=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<List<Appointment>>(myAppointment, status);
    }
}
