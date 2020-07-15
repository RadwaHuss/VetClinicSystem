package org.eme.petrestfulapp.repository;

import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {

    List<Clinic> findByPhoneOrAddress(String phone, String address);

    Clinic findByName(String name);
}
