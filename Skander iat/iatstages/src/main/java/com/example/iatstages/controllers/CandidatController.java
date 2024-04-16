package com.example.iatstages.controllers;


import com.example.iatstages.entities.Candidature;
import com.example.iatstages.entities.Sujet;
import com.example.iatstages.services.CandidatureService.CandidatureService;
import com.example.iatstages.services.CandidatureService.FileServiceImpl;
import com.example.iatstages.services.SujetService.SujetService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/candidat")
@RequiredArgsConstructor
public class CandidatController {

    private final SujetService sujetService;
    private final CandidatureService candidatureService;
    private final FileServiceImpl fileService;


    // Sujet
    @GetMapping("/getAllSujet")
    public ResponseEntity<List<Sujet>> getAllSujet(){
        List<Sujet> sujetsList = sujetService.getAllSujets();
        return ResponseEntity.ok(sujetsList);
    }

    // Candidature
    @PostMapping("/addCandidature/{idUser}/{idSujet}")
    public ResponseEntity<Candidature> addCandidature(@PathVariable Long idUser, @PathVariable Long idSujet, @RequestBody Candidature candidature) {
        Candidature newCandidature = candidatureService.addCandidature(idUser, idSujet, candidature);
        return ResponseEntity.ok(newCandidature);
    }

    @GetMapping("/getCandidatureById/{id}")
    public ResponseEntity<Candidature> getCandidatureById(@PathVariable Long id) {
        Candidature candidature = candidatureService.getCandidatureById(id);
        return ResponseEntity.ok(candidature);
    }

    @PutMapping("/updateCandidature/{id}")
    public ResponseEntity<Candidature> updateCandidature(@PathVariable Long id, @RequestBody Candidature candidature) {
        Candidature updatedCandidature = candidatureService.updateCandidature(id, candidature);
        return ResponseEntity.ok(updatedCandidature);
    }

    @DeleteMapping("/deleteCandidature/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Long id) {
        candidatureService.deleteCandidature(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getCandidaturesByUser/{idUser}")
    public ResponseEntity<List<Candidature>> getCandidaturesByUser(@PathVariable Long idUser) {
        List<Candidature> candidatures = candidatureService.getCandidaturesByUser(idUser);
        return ResponseEntity.ok(candidatures);
    }

    @PostMapping("/uploadCv/{idCandidature}")
    public ResponseEntity<Void> uploadCv(@PathVariable Long idCandidature, @RequestParam("file") MultipartFile file) {
        fileService.saveCv(file, idCandidature);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadCv/{idCandidature}")
    public ResponseEntity<Resource> downloadCv(@PathVariable Long idCandidature) {
        Resource resource = fileService.loadCv(idCandidature);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
