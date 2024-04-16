package com.example.iatstages.controllers;

import com.example.iatstages.entities.Candidature;
import com.example.iatstages.entities.Sujet;
import com.example.iatstages.services.CandidatureService.CandidatureService;
import com.example.iatstages.services.SujetService.SujetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {
    private final SujetService sujetService;
    private final CandidatureService candidatureService;

    // Sujet
    @GetMapping("/getAllSujets")
    public ResponseEntity<?> getAllSujets() {
        return ResponseEntity.ok(sujetService.getAllSujets());
    }
    @PostMapping("/addSujet")
    public ResponseEntity<?> addSujet(@RequestBody Sujet sujet) {
       return ResponseEntity.ok(sujetService.addSujet(sujet));
    }
    @GetMapping("/getSujetById/{id}")
    public ResponseEntity<Sujet> getSujetById(@PathVariable Long id){
        return ResponseEntity.ok(sujetService.getSujetById(id));
    }

    @PutMapping("/updateSujet/{idSujet}")
    public ResponseEntity<?> updateSujet(@PathVariable Long idSujet, @RequestBody Sujet sujet) {
        return ResponseEntity.ok(sujetService.updateSujet(idSujet, sujet));
    }
    @DeleteMapping("/deleteSujet/{id}")
    public ResponseEntity<?> deleteSujet (@PathVariable Long id){
        sujetService.deleteSujet(id);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    // Candidat
    @GetMapping("/getAllCandidatures")
    public ResponseEntity<List<Candidature>> getAllCandidatures() {
        List<Candidature> candidatures = candidatureService.getAllCandidatures();
        return ResponseEntity.ok(candidatures);
    }

    @GetMapping("/getCandidaturesBySujet/{idSujet}")
    public ResponseEntity<List<Candidature>> getCandidaturesBySujet(@PathVariable Long idSujet) {
        List<Candidature> candidatures = candidatureService.getCandidaturesBySujet(idSujet);
        return ResponseEntity.ok(candidatures);
    }

}