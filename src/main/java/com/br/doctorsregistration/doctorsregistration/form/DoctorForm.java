package com.br.doctorsregistration.doctorsregistration.form;

import com.br.doctorsregistration.doctorsregistration.model_entities.Doctor;
import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;
import com.br.doctorsregistration.doctorsregistration.service.SpecialtyService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorForm {

    @NotEmpty @NotNull @Length(min=2)
    private String name;
    @NotEmpty @NotNull @Length(min=5)
    private String crm;
    @NotEmpty @NotNull
    private String email;
    @NotEmpty @NotNull
    private String nomeSpecialty;

    public Doctor converte(SpecialtyService specialtyService) {

        Specialty specialty = specialtyService.findBySpecialty(nomeSpecialty);

        return new Doctor(name, crm, email, specialty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeSpecialty() {
        return nomeSpecialty;
    }

    public void setNomeSpecialty(String nomeSpecialty) {
        this.nomeSpecialty = nomeSpecialty;
    }
}
