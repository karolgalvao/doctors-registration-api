package com.br.doctorsregistration.doctorsregistration.dto;


import com.br.doctorsregistration.doctorsregistration.model_entities.Doctor;
import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;
import org.springframework.data.domain.Page;

public class DoctorDto {

    private String name;
    private String crm;
    private String email;
    private Specialty specialty;


    public DoctorDto (Doctor doctor){
        this.name = doctor.getName();
        this.crm = doctor.getCrm();
        this.email = doctor.getEmail();
        this.specialty = doctor.getSpecialty();
    }

    public static Page<DoctorDto> converter(Page<Doctor> doctor){

        return doctor.map(DoctorDto::new);
    }

    public String getName() {
        return name;
    }

    public String getCrm() {
        return crm;
    }

    public String getEmail() {
        return email;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
}
