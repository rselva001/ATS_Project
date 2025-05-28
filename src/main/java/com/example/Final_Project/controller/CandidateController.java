package com.example.Final_Project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Final_Project.entity.Candidate;
import com.example.Final_Project.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // ✅ Create a new candidate
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return ResponseEntity.ok(candidateService.createCandidate(candidate));
    }

    // ✅ Get all candidates
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // ✅ Get candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        return candidate.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Candidate not found with ID: " + id));
    }

    // ✅ Update candidate
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updated = candidateService.updateCandidate(id, candidate);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(404).body("Candidate not found for update with ID: " + id);
        }
    }

    // ✅ Delete candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        boolean deleted = candidateService.deleteCandidate(id);
        if (deleted) {
            return ResponseEntity.ok("Candidate deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Candidate not found with ID: " + id);
        }
    }

    // ✅ Get candidate by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCandidateByEmail(@PathVariable String email) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(email);
        return candidate.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Candidate not found with email: " + email));
    }
}