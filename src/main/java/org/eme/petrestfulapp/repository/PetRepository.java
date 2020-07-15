package org.eme.petrestfulapp.repository;

import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

    List<Pet> findByOwner(Owner owner);

}
