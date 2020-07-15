package org.eme.petrestfulapp.repository;

import org.eme.petrestfulapp.model.Owner;
import org.eme.petrestfulapp.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {

}
