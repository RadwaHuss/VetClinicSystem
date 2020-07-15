package org.eme.petrestfulapp.service;

import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;

import java.util.List;

public interface ClinicService {
    List<Clinic> findAll();

    Clinic findById(Long id);

    List<Clinic> findByPhoneOrAddress(String phone, String address);

    Clinic save(Clinic clinic);

    void delete(Long id);

    void addDoctor(Clinic clinic, Doctor doctor);

    void removeDoctor(Clinic clinic, Doctor doctor);

    Clinic findByName(String name);
}
