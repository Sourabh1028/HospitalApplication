package com.geekster.HospitalApplication.Controller;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Service.DoctorService;
import com.geekster.HospitalApplication.Service.PatientService;
import com.geekster.HospitalApplication.Service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/suggestion")
public class suggestionController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    SuggestionService suggestionService;

    @GetMapping("/suggestDoctors/{patientId}")
    public ResponseEntity<String> suggestDoctors(@PathVariable Integer patientId) {
        try {
            List<Doctor> suggestedDoctors = suggestionService.suggestDoctors(patientId);
            return new ResponseEntity<>(suggestedDoctors.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(handleException(e), HttpStatus.NOT_FOUND);
        }
    }

    private String handleException(Exception exception) {
        if (exception.getMessage() != null) {
            return exception.getMessage();
        } else {
            return "An error occurred while processing your request.";
        }
    }
}
