package com.example.iatstages.controllers;

import com.example.iatstages.entities.Test;
import com.example.iatstages.services.TestService.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
@CrossOrigin("*")
public class TesttController {

     @Autowired
    private testInterfce testInterface;

    @GetMapping
    public ResponseEntity<List<Test>> getAllTests() {
        List<Test> tests = testInterface.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }






    @GetMapping("/{testId}")
    public ResponseEntity<Test> getTestById(@PathVariable Long testId) {
        Test test = testInterface.getTestById(testId);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/getSujet/{sujetId}")
    public ResponseEntity<List<Test>> getTestsBySujetId(@PathVariable Long sujetId) {
        List<Test> tests = testInterface.getTestsBySujetId(sujetId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test updatedTest) {
        Test result = testInterface.updateTest(id, updatedTest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        testInterface.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{sujetId}")
    public ResponseEntity<Test> createTest(@PathVariable Long sujetId, @RequestBody Test test) {
        Test createdTest = testInterface.createTest(sujetId, test);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

}
