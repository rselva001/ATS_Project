package com.example.Final_Project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    // ✅ Get top N candidates (e.g., top 4)
//    @GetMapping("/top")
//    public ResponseEntity<List<Candidate>> getTopCandidates() {
//        List<Candidate> candidates = candidateService.getTopCandidates(4);
//        return ResponseEntity.ok(candidates);
//    }

    // ✅ Get candidate by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCandidateByEmail(@PathVariable String email) {
        Optional<Candidate> candidate = candidateService.getCandidateByEmail(email);
        return candidate.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Candidate not found with email: " + email));
    }

    // ✅ Get candidate by ID (keep at the bottom to avoid conflicts)
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
    
    // Search by name: GET /api/candidates/search?name=John
    @GetMapping("/search")
    public ResponseEntity<List<Candidate>> searchCandidates(@RequestParam String name) {
        List<Candidate> result = candidateService.searchCandidatesByName(name);
        return ResponseEntity.ok(result);
    }
}
