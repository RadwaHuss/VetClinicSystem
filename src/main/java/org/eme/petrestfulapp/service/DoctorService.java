package org.eme.petrestfulapp.service;

import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();

    List<Doctor> findByClinic(Clinic clinic);

    Doctor findById(Long id);

    Doctor save(Doctor doctor);

    void delete(Long id);

    Doctor findByName(String name);
}
