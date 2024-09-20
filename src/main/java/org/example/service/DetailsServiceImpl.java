package org.example.service;

import org.example.entity.Details;
import org.example.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetailsServiceImpl implements DetailsService{
    @Autowired
    private DetailsRepository detailsRepository;
    @Override
    public Details saveDetails(Details details) {
        return detailsRepository.save(details);
    }

    @Override
    public List<Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    @Override
    public Details getDetailsById(Long id) {
        return detailsRepository.findById(id).orElse(null);
    }
}
