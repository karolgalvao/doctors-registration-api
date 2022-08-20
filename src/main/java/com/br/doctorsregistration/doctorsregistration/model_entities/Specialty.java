package com.br.doctorsregistration.doctorsregistration.model_entities;

import javax.persistence.*;

@Entity
@Table(name="tb_specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String specialty;

    public Specialty() {
    }

    public Specialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
