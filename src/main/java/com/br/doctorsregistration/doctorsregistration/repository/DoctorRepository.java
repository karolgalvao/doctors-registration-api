package com.br.doctorsregistration.doctorsregistration.repository;

import com.br.doctorsregistration.doctorsregistration.model_entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByCrm(String crm, Pageable pageable);
    Page<Doctor> findBySpecialty_Specialty(String nomeSpecialty, Pageable pageable);

    Optional<Doctor> findByName(String name);

}
