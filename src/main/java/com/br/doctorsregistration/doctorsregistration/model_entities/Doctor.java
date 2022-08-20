package com.br.doctorsregistration.doctorsregistration.model_entities;

import javax.persistence.*;

@Entity
@Table(name="tb_doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 70)
    private String name;
    @Column(nullable = false)
    private String crm;
    @Column(nullable = false)
    private String email;

    @ManyToOne
    private Specialty specialty;

    public Doctor() {
    }

    public Doctor(String name, String crm, String email, Specialty specialty){
        this.name = name;
        this.crm = crm;
        this.email = email;
        this.specialty = specialty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
