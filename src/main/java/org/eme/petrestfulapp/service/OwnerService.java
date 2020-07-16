package org.eme.petrestfulapp.service;

import org.eme.petrestfulapp.model.Doctor;
import org.eme.petrestfulapp.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> findAll();

    Owner findById(Long id);

    Owner save(Owner owner);

    void delete(Long id);

    Owner findByName(String name);
}
