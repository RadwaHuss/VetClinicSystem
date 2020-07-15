package org.eme.petrestfulapp.controllers;

import org.eme.petrestfulapp.exception.EntityFoundException;
import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAll(){
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable("id") Long id){
        return doctorService.findById(id);
    }

    @PostMapping
    public Doctor create(@Valid @RequestBody Doctor doctor){
        Doctor foundedDoctor = doctorService.findByName(doctor.getName());
        if(foundedDoctor !=null &&foundedDoctor.getId()>0){
            throw new EntityFoundException("Doctor with name :" +doctor.getName() + " is already existed");
        }
        return doctorService.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable("id") Long id,@Valid @RequestBody Doctor doctor){
        Doctor doctorFound = doctorService.findById(id);
        if(doctorFound != null){
            doctor.setId(id);
            doctorService.save(doctor);
        }
        return doctor;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        doctorService.delete(id);
    }


}
