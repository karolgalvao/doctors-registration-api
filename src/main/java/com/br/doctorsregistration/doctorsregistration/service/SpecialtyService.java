package com.br.doctorsregistration.doctorsregistration.service;

import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;
import com.br.doctorsregistration.doctorsregistration.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecialtyService {

    @Autowired
    SpecialtyRepository specialtyRepository;

    public Specialty findBySpecialty(String nomeSpecialty) {
        return specialtyRepository.findBySpecialty(nomeSpecialty);
    }

    public Optional<Specialty> getSpecialty(String nomeSpecialty) {
        return Optional.ofNullable(specialtyRepository.findBySpecialty(nomeSpecialty));
    }

    public Optional<Specialty> findBySpecialtyOptional(String nomeSpecialty) {
        return Optional.ofNullable(specialtyRepository.findBySpecialty(nomeSpecialty));
    }
}
