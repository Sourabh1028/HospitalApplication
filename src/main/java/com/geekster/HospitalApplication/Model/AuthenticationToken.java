package com.geekster.HospitalApplication.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_patient_Id")
    private Patient patient;

    public AuthenticationToken(Patient patient) {
        this.tokenCreationDate = LocalDate.now();
        this.patient = patient;
        this.token = UUID.randomUUID().toString();

    }
}
