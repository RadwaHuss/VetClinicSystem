package org.eme.petrestfulapp.service.impl;

import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.repository.DoctorRepository;
import org.eme.petrestfulapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findByClinic(Clinic clinic) {

        return doctorRepository.findByClinic(clinic);
    }


    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("No Doctor with (%s) found in db",id)));
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        Doctor doctor = findById(id);
        if(doctor!=null){
            doctorRepository.delete(doctor);
        }
    }

    @Override
    public Doctor findByName(String name) {
        return doctorRepository.findByName(name);
    }
}
