package org.eme.petrestfulapp.controllers;


import org.eme.petrestfulapp.model.Clinic;
import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.service.ClinicService;
import org.eme.petrestfulapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @GetMapping
    public List<Owner> getAll(){
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public Owner getById(@PathVariable("id") Long id){
        return ownerService.findById(id);
    }

    @PostMapping
    public Owner create(@Valid @RequestBody Owner owner){

        return ownerService.save(owner);
    }

    @PutMapping("/{id}")
    public Owner update(@PathVariable("id") Long id,@Valid @RequestBody Owner owner){
        Owner ownerFound = ownerService.findById(id);
        if(ownerFound != null){
            owner.setId(id);
            ownerService.save(owner);
        }

        return  owner;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        ownerService.delete(id);
    }
}
