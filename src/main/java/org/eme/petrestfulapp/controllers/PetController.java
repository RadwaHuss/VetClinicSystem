package org.eme.petrestfulapp.controllers;


import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.model.Pet;
import org.eme.petrestfulapp.service.OwnerService;
import org.eme.petrestfulapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private OwnerService ownerService;
    @GetMapping
    public List<Pet> getAll(){
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable("id") Long id){
        return petService.findById(id);
    }

    @PostMapping
    public Pet create(@Valid @RequestBody Pet pet){
        System.out.println("post pet");
        return petService.save(pet);
    }

    @PutMapping("/{id}")
    public Pet update(@PathVariable("id") Long id,@Valid @RequestBody Pet pet){
        Pet petFound = petService.findById(id);
        if(petFound != null){
            pet.setId(id);
            petService.save(pet);
        }
        return  pet;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        petService.delete(id);
    }

    @GetMapping("/owner/{id}")
    public List<Pet> findByOwner(@PathVariable("id") Long id) {
        Owner owner = new Owner();
        owner.setId(id);
        return petService.findByOwner(owner);
    }

    @PostMapping("/{petId}/owner/{ownerId}")
    public Pet assignPetToOwner(@PathVariable("petId") Long petId, @PathVariable("ownerId") Long ownerId) {
        Pet pet = petService.findById(petId);
        if(pet != null){
            Owner owner = ownerService.findById(ownerId);
            if(owner!=null){
                pet.setOwner(owner);
                owner.getPets().add(pet);
                petService.save(pet);
            }
        }
        return pet;
    }
}
