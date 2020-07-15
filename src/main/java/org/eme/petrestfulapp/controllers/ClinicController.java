package org.eme.petrestfulapp.controllers;


import org.eme.petrestfulapp.exception.EntityFoundException;
import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.service.ClinicService;
import org.eme.petrestfulapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Clinic> getAll(
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "address", required = false) String address){
        List<Clinic> clinicList= new ArrayList<>(0);
        if(phone!=null || address!=null){
            clinicList = clinicService.findByPhoneOrAddress(phone, address);
        }else {
            clinicList = clinicService.findAll();
        }
        return clinicList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(clinicService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/{clinicId}/doctors")
    public Set<Doctor> getByClinic(@PathVariable("clinicId") Long clinicId) {
        return clinicService.findById(clinicId).getDoctors();
    }

    @PostMapping
    public ResponseEntity<Clinic> create(@Valid  @RequestBody Clinic clinic){
        Clinic founded = clinicService.findByName(clinic.getName());
        if(founded !=null &&founded.getId()>0){
            throw new EntityFoundException("Clinic with name :"+clinic.getName() +" is already existed ");
        }
        return new ResponseEntity<>(clinicService.save(clinic), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Clinic update(@PathVariable("id") Long id,@Valid @RequestBody Clinic clinic){
        Clinic clinicFounded = clinicService.findById(id);
        if(clinicFounded != null){
            clinic.setId(id);
            clinicService.save(clinic);
        }
        return clinic;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        clinicService.delete(id);
    }

    @PostMapping("/{clinicId}/doctors/{doctorId}")
    public Clinic assignDoctorToClinic(@PathVariable("clinicId") Long clinicId, @PathVariable("doctorId") Long doctorId) {
        Clinic clinic = clinicService.findById(clinicId);
        if(clinic != null){
            Doctor doctor = doctorService.findById(doctorId);
            if(doctor!=null){
                doctor.setClinic(clinic);
                clinic.getDoctors().add(doctor);
                clinicService.save(clinic);
            }
        }
        return clinic;
    }

    @DeleteMapping("/{clinicId}/doctors/{doctorId}")
    public Clinic removeDoctorFromClinic(@PathVariable("clinicId") Long clinicId, @PathVariable("doctorId") Long doctorId) {
        Clinic clinic = clinicService.findById(clinicId);
        Doctor doctor = doctorService.findById(doctorId);

        if(!clinic.getDoctors().contains(doctor)){
            throw new NotFoundException("This doctor: "+ doctor.getName()+ " doesn't exist in this clinic: "+clinic.getName());
        }
        clinic.getDoctors().remove(doctor);
        doctor.setClinic(null);
        clinicService.save(clinic);
        return clinic;
    }

}
