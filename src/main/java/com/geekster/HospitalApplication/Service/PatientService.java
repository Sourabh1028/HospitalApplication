package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Dto.SignInInput;
import com.geekster.HospitalApplication.Dto.SignInOutput;
import com.geekster.HospitalApplication.Dto.SignUpInput;
import com.geekster.HospitalApplication.Dto.SignUpOutput;
import com.geekster.HospitalApplication.Model.AppointmentKey;
import com.geekster.HospitalApplication.Model.AuthenticationToken;
import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Model.Patient;
import com.geekster.HospitalApplication.Repositary.PatientRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    AuthenticationService tokenService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    public SignUpOutput signUp(SignUpInput signUpInput){

        //Check already exist by email

        Patient patient = patientRepo.findFirstByPatientEmail(signUpInput.getUserEmail());

        if(patient != null){
            throw new IllegalStateException("Patient already Exist...!!!");
        }

        //encryption

        String encryptedPassword = null;
        try {
             encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //save the User

        patient = new Patient(signUpInput.getUserFirstName(),signUpInput.getUserLastName(),
                signUpInput.getUserEmail(), encryptedPassword, signUpInput.getUserContact());

        patientRepo.save(patient);

        //token Creation and saving
        AuthenticationToken token = new AuthenticationToken(patient);
        tokenService.saveToken(token);


        return new SignUpOutput("Patient Registered", "Patient Created Successfully...!!!");
    }



    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInInput){
        //get email
        Patient patient = patientRepo.findFirstByPatientEmail(signInInput.getPatientEmail());
        if(patient == null){
            throw new IllegalStateException("Patient Invalid...!!!");
        }

        //encrypt password
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInInput.getPatientPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //match it with data base encrypted pass
        boolean isPasswordValid = encryptedPassword.equals(patient.getPatientPassword());

        if(!isPasswordValid){
            throw new IllegalStateException("Patient Invalid...!!!");
        }

        //figure out token
        AuthenticationToken authToken = tokenService.getToken(patient);

        //set up output response
        return new SignInOutput("Authentication Successfull...!!1",authToken.getToken());

    }

    public List<Doctor> getAllDocs() {
        return doctorService.getAllDocs();
    }

    public void cancelAppointment(AppointmentKey key) {
        appointmentService.cancelAppointment(key);
    }
}
