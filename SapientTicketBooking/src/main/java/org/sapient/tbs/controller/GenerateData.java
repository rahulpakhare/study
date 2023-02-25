package org.sapient.tbs.controller;

import org.sapient.tbs.repository.model.City;
import org.sapient.tbs.service.GenerateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class GenerateData {
    @Autowired
    private GenerateDataService generateDataService;

    @GetMapping("/generate/testdata")
    public ResponseEntity<?>  generateTestData() {
        generateDataService.generateTestData();
        return ResponseEntity.status(HttpStatus.OK).body("data generated");
    }
}
