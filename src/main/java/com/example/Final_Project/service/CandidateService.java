package com.example.Final_Project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Final_Project.entity.Candidate;
import com.example.Final_Project.repository.CandidateRepository;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // ✅ CREATE a new candidate
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // ✅ READ all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // ✅ READ candidate by ID
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    // ✅ UPDATE candidate
    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Optional<Candidate> existingOpt = candidateRepository.findById(id);
        if (existingOpt.isPresent()) {
            Candidate existing = existingOpt.get();
            existing.setName(updatedCandidate.getName());
            existing.setEmail(updatedCandidate.getEmail());
            existing.setPhone(updatedCandidate.getPhone());
            existing.setResumeUrl(updatedCandidate.getResumeUrl());
            return candidateRepository.save(existing);
        }
        return null;
    }

    // ✅ DELETE candidate
    public boolean deleteCandidate(Long id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Optional: Get candidate by email
    public Optional<Candidate> getCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
    
    // Search candidates by name
    public List<Candidate> searchCandidatesByName(String name) {
        return candidateRepository.findByNameContainingIgnoreCase(name);
    }
    
//    
//    public List<Candidate> getTopCandidates(int count) {
//        // Fetch all candidates
//        List<Candidate> allCandidates = candidateRepository.findAll();
//
//        // Sort by ID in descending order and return the top 'count' candidates
//        return allCandidates.stream()
//                .sorted((c1, c2) -> Long.compare(c2.getId(), c1.getId()))
//                .limit(count)
//                .collect(Collectors.toList());
//    }


}