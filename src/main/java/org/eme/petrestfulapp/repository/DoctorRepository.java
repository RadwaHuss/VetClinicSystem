package org.eme.petrestfulapp.repository;

import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findByClinic(Clinic clinic);

    Doctor findByName(String name);
}
