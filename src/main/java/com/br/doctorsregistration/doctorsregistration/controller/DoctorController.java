package com.br.doctorsregistration.doctorsregistration.controller;

import com.br.doctorsregistration.doctorsregistration.dto.DoctorDto;
import com.br.doctorsregistration.doctorsregistration.form.DoctorForm;
import com.br.doctorsregistration.doctorsregistration.model_entities.Doctor;
import com.br.doctorsregistration.doctorsregistration.model_entities.Specialty;
import com.br.doctorsregistration.doctorsregistration.service.DoctorService;
import com.br.doctorsregistration.doctorsregistration.service.SpecialtyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    SpecialtyService specialtyService;

    @GetMapping
    @Cacheable(value="listDoctor")
    public Page<DoctorDto> listDoc(String crm, @PageableDefault(page=0, size=5, sort="name", direction = Sort.Direction.ASC) Pageable pageable ){

        if(crm != null){
            return DoctorDto.converter(doctorService.searchDoctorByCrm(crm, pageable));
        }

        return DoctorDto.converter(doctorService.getDoctors(pageable));
    }

    @GetMapping("/specialty")
    @Cacheable(value="listDoctor")
    public Page<DoctorDto> listDocSpecialty(String nomeSpecialty, @PageableDefault(page=0, size=2, sort="name", direction = Sort.Direction.ASC) Pageable pageable ){
        if(nomeSpecialty != null){
            return DoctorDto.converter(doctorService.searchDoctorByNomeSpecialty(nomeSpecialty, pageable));
        }

        return DoctorDto.converter(doctorService.getDoctors(pageable));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value="listDoctor", allEntries = true)
    public ResponseEntity<Object> saveDoctor(@RequestBody @Valid DoctorForm doctorForm) {

        Optional<Specialty> specialtyOptional = specialtyService.findBySpecialtyOptional(doctorForm.getNomeSpecialty());

        if (!specialtyOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Specialty has not been found!");
        }

        Optional<Doctor> doctorOptional = doctorService.getDoctor(doctorForm.getName());

        if (!doctorOptional.isPresent()) {
            Doctor doctor = doctorForm.converte(specialtyService);
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(doctor));

        } else if (!(Objects.equals(doctorOptional.get().getSpecialty().getSpecialty(), doctorForm.getNomeSpecialty()))) {
            Doctor doctor = doctorForm.converte(specialtyService);
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(doctor));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Doctor is already registered!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value="listDoctor", allEntries = true)
    public ResponseEntity<Object> deleteDoctor(@PathVariable Long id){
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);

        if(!doctorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found!");
        } else {
            doctorService.deleteDoctor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted successfully!");
        }

    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value="listDoctor", allEntries = true)
    public ResponseEntity<Object> updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorForm doctorForm){
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);

        if(!doctorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found!");
        }
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorForm, doctor);
        doctor.setSpecialty(doctorOptional.get().getSpecialty());
        doctor.setId(doctorOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.saveDoctor(doctor));

    }



}
