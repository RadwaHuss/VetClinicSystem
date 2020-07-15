package org.eme.petrestfulapp.service.impl;

import org.eme.petrestfulapp.exception.NotFoundException;
import org.eme.petrestfulapp.model.Visit;
import org.eme.petrestfulapp.repository.VisitRepository;
import org.eme.petrestfulapp.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public List<Visit> findAll() {

        return visitRepository.findAll();
    }

    @Override
    public Visit findById(Long id) {

        return visitRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("No visit with (%s) found in db",id)));
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Long id) {
        Visit visit = findById(id);
        if(visit!=null){
            visitRepository.delete(visit);
        }
    }
}
