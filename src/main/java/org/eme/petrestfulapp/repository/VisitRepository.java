package org.eme.petrestfulapp.repository;

import org.eme.petrestfulapp.model.Pet;
import org.eme.petrestfulapp.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {

}
