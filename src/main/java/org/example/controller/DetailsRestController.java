package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Details;
import org.example.service.DetailsService;
import org.example.utils.LogMethodParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DetailsRestController {
    @Autowired
    private DetailsService detailsService;
    @PostMapping("/detail")
    public ResponseEntity<Details> saveDetails(@Valid @RequestBody Details details){
        return ResponseEntity.ok(detailsService.saveDetails(details));
    }

    @GetMapping("/details")
    public ResponseEntity<List<Map<String, Object>>> getAllDetsils() {
        List<Details> details = detailsService.getAllDetails();
        return ResponseEntity.ok(buildHierarchy(details));
    }

    @GetMapping("/detail/{id}")
    @LogMethodParam
    public ResponseEntity<Details> getCharacterById(@PathVariable Long id) {
        return ResponseEntity.ok(detailsService.getDetailsById(id));
    }

    // Algorithm to build parent-child hierarchy
    private List<Map<String, Object>> buildHierarchy(List<Details> details) {
        // Step 1: Create a map of characters by ID
        Map<Long, Map<String, Object>> detailMap = details.stream()
                .collect(Collectors.toMap(Details::getId, detail -> {
                    Map<String, Object> obj = new HashMap<>();
                    obj.put("Name", detail.getName());
                    obj.put("Color", detail.getColor());
                    obj.put("Sub Classes", new ArrayList<>());
                    return obj;
                }));

        // Step 2: Build hierarchy
        List<Map<String, Object>> rootDetails = new ArrayList<>();
        for (Details detail : details) {
            if (detail.getParentId() == 0) {
                rootDetails.add(detailMap.get(detail.getId()));
            } else {
                Map<String, Object> parent = detailMap.get(detail.getParentId());
                if (parent != null) {
                    ((List<Map<String, Object>>) parent.get("Sub Classes")).add(detailMap.get(detail.getId()));
                }
            }
        }
        return rootDetails;
    }


}
