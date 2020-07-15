package org.eme.petrestfulapp.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.*;
import org.eme.petrestfulapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PetService petService;


    @GetMapping
    public List<Visit> getAll(){
        return visitService.findAll();
    }

    @GetMapping("/{id}")
    public Visit getById(@PathVariable("id") Long id){
        return visitService.findById(id);
    }

    @PostMapping("/pet/{petId}/doctor/{doctorId}/clinic/{clinicId}")
    public ResponseEntity<Visit> create(@PathVariable("petId") Long petId,
                        @PathVariable("doctorId") Long doctorId,
                        @PathVariable("clinicId") Long clinicId
                       ){


        Pet pet = petService.findById(petId);
        Doctor doctor = doctorService.findById(doctorId);
        Clinic clinic = clinicService.findById(clinicId);

        if(!clinic.getDoctors().contains(doctor)){
            throw new NotFoundException("This doctor: "+ doctor.getName()+ " doesn't exist in this clinic: "+clinic.getName());
        }
        Visit visit = new Visit();
        visit.setPet(pet);
        visit.setDoctor(doctor);
        visit.setClinic(clinic);
        visit.setDate(new Date());

        return new ResponseEntity<>(visitService.save(visit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Visit update(@PathVariable("id") Long id,@Valid @RequestBody Visit visit){
        Visit visitFound = visitService.findById(id);
        if(visitFound != null){
            visit.setId(id);
            visitService.save(visit);
        }
        return  visit;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        visitService.delete(id);
    }
}
