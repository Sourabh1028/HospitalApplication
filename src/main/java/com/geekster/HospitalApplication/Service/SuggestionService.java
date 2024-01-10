package com.geekster.HospitalApplication.Service;

import com.geekster.HospitalApplication.Model.Doctor;
import com.geekster.HospitalApplication.Model.Patient;
import com.geekster.HospitalApplication.Model.Speciality;
import com.geekster.HospitalApplication.Model.Symptom;
import com.geekster.HospitalApplication.Repositary.DoctorRepo;
import com.geekster.HospitalApplication.Repositary.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public List<Doctor> suggestDoctors(Integer patientId) throws Exception {
        Optional<Patient> patientOptional = patientRepo.findById(patientId);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            String patientCity = patient.getCity();
            Symptom patientSymptom = patient.getSymptom();

            if (!isLocationSupported(patientCity)) {
                throw new Exception("We are still waiting to expand to your location");
            }

            Speciality speciality = mapSymptomToSpeciality(patientSymptom);

            List<Doctor> suggestedDoctors = doctorRepo.findByCityAndSpeciality(patientCity, speciality);
            if (suggestedDoctors.isEmpty()) {
                throw new Exception("There isn't any doctor present at your location for your symptom");
            }

            return suggestedDoctors;
        } else {
            throw new Exception("Patient with ID " + patientId + " not found");
        }
    }

    private boolean isLocationSupported(String location) {
        return List.of("Delhi", "Noida", "Faridabad").contains(location);
    }

    private Speciality mapSymptomToSpeciality(Symptom symptom) {

        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT_SPECIALIST;
            default:
                // For unknown symptoms
                return Speciality.GENERAL_MEDICINE;
        }
    }
}
