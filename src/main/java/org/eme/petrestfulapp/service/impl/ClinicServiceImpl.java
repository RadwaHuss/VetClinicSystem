package org.eme.petrestfulapp.service.impl;

import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.repository.ClinicRepository;
import org.eme.petrestfulapp.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic findById(Long id) {
        return clinicRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("No clinic with (%s) found in db",id)));
    }

    @Override
    public List<Clinic> findByPhoneOrAddress(String phone, String address) {
        return clinicRepository.findByPhoneOrAddress(phone,address);
    }

    @Override
    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public void delete(Long id) {
        Clinic doctor = findById(id);
        if(doctor!=null){
            clinicRepository.delete(doctor);
        }
    }

    @Override
    public void addDoctor(Clinic clinic, Doctor doctor) {
        clinic.getDoctors().add(doctor);
        doctor.setClinic(clinic);
        save(clinic);
    }

    @Override
    public void removeDoctor(Clinic clinic, Doctor doctor) {
        clinic.getDoctors().remove(doctor);
        save(clinic);
    }

    @Override
    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }
}
