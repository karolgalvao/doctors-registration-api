package com.br.doctorsregistration.doctorsregistration.repository;

import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty findBySpecialty(String nomeSpecialty);
}
