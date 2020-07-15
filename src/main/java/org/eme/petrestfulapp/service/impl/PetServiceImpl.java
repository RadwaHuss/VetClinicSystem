package org.eme.petrestfulapp.service.impl;

import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.model.Pet;
import org.eme.petrestfulapp.model.Visit;
import org.eme.petrestfulapp.repository.PetRepository;
import org.eme.petrestfulapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> findByOwner(Owner owner) {
        return petRepository.findByOwner(owner);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("No pet with (%s) found in db",id)));
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Long id) {
        Pet pet = findById(id);
        if(pet!=null){
            petRepository.delete(pet);
        }
    }
}
