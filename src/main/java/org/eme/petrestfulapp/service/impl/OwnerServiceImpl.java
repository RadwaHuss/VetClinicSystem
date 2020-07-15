package org.eme.petrestfulapp.service.impl;

import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.repository.OwnerRepository;
import org.eme.petrestfulapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("No owner with (%s) found in db",id)));
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Long id) {
        Owner owner = findById(id);
        if(owner!=null){
            ownerRepository.delete(owner);
        }
    }

}
