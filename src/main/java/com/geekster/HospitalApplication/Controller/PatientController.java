package com.geekster.HospitalApplication.Controller;

import com.geekster.HospitalApplication.Dto.SignInInput;
import com.geekster.HospitalApplication.Dto.SignInOutput;
import com.geekster.HospitalApplication.Dto.SignUpInput;
import com.geekster.HospitalApplication.Dto.SignUpOutput;
import com.geekster.HospitalApplication.Model.AppointmentKey;
import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Service.AuthenticationService;
import com.geekster.HospitalApplication.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    AuthenticationService authenticationService;

    //Sign_Up
    @PostMapping("/signUp")
    public SignUpOutput signup(@RequestBody SignUpInput signUpInput)
    {
        return patientService.signUp(signUpInput);
    }

    //sign_In
    @PostMapping("/signIn")
    public SignInOutput signIp(@RequestBody SignInInput signInInput)
    {
        return patientService.signIn(signInInput);
    }

    @GetMapping("/docs")
    public ResponseEntity<List<Doctor>> getAllDocs(@RequestParam String userEmail, @RequestParam String token){

        //authenticate
        HttpStatus status;
        List<Doctor> allDocList = null;
        if(authenticationService.authenticate(userEmail, token)){
            allDocList = patientService.getAllDocs();
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<List<Doctor>>(allDocList, HttpStatus.OK);
    }

    @DeleteMapping("deleteapp")
    ResponseEntity<Void> cancelAppointment(@RequestParam String userEmail, @RequestParam String token, @RequestBody AppointmentKey key){

        HttpStatus status;
        if(authenticationService.authenticate(userEmail, token)){

            patientService.cancelAppointment(key);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<Void>(status);
    }

}
