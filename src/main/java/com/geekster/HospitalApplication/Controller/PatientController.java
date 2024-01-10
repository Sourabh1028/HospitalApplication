package com.geekster.HospitalApplication.Controller;

import com.geekster.HospitalApplication.Model.Patient;
import com.geekster.HospitalApplication.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/addPatient")
    ResponseEntity<String> addPatient(@RequestBody @Valid Patient patient){
        patientService.addPatient(patient);
        return new ResponseEntity<>("Patient added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/removePatient/{patientId}")
    ResponseEntity<String> deletePatient(@PathVariable Integer patientId){
        patientService.removePatient(patientId);
        return new ResponseEntity<>("Patient removed successfully",HttpStatus.OK);
    }

}
