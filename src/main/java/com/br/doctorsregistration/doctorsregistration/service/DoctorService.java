package com.br.doctorsregistration.doctorsregistration.service;

import com.br.doctorsregistration.doctorsregistration.model_entities.Doctor;
import com.br.doctorsregistration.doctorsregistration.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public Page<Doctor> getDoctors(Pageable pageable){
        return doctorRepository.findAll(pageable);
    }

    public Page<Doctor> searchDoctorByCrm(String crm, Pageable pageable) {
        return doctorRepository.findByCrm(crm, pageable);
    }

    public Page<Doctor> searchDoctorByNomeSpecialty(String nomeSpecialty, Pageable pageable) {
        return doctorRepository.findBySpecialty_Specialty(nomeSpecialty, pageable);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public Optional<Doctor> getDoctor(String name) {
        return doctorRepository.findByName(name);
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
