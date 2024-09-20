package org.example.service;

import org.example.entity.Details;

import java.util.List;

public interface DetailsService {
    public Details saveDetails(Details details);
    public List<Details> getAllDetails();
    public Details getDetailsById(Long id);
}
