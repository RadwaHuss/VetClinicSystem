package org.eme.petrestfulapp.service;

import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetService {
    List<Pet> findAll();

    List<Pet> findByOwner(Owner owner);

    Pet findById(Long id);

    Pet save(Pet pet);

    void delete(Long id);

}
