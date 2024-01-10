package com.geekster.HospitalApplication.Controller;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Service.DoctorService;
import jakarta.validation.Valid;
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

    @PostMapping("/addDoctor")
    ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return new ResponseEntity<>("Doctor added successfully...!", HttpStatus.CREATED);
    }

    @GetMapping("/getAllDoctors")
    public List<Doctor> getAllUser(){
        return doctorService.getAllDocs();
    }

    @DeleteMapping("/removeDoctor/{doctorId}")
    ResponseEntity<String> removeDoctor(@PathVariable Long doctorId){
        doctorService.removeDoctor(doctorId);
        return new ResponseEntity<>("Doctor removed successfully...!", HttpStatus.FOUND);
    }

}
