package org.eme.petrestfulapp.service;

import org.eme.petrestfulapp.model.Pet;
import org.eme.petrestfulapp.model.Visit;

import java.util.List;

public interface VisitService {
    List<Visit> findAll();

    Visit findById(Long id);

    Visit save(Visit visit);

    void delete(Long id);

}
