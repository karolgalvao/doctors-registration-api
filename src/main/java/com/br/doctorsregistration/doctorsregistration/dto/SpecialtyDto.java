package com.br.doctorsregistration.doctorsregistration.dto;

import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;

public class SpecialtyDto {

    private String specialty;

    public SpecialtyDto(Specialty specialty){
        this.specialty = specialty.getSpecialty();
    }

    public String getSpecialty() {
        return specialty;
    }
}
