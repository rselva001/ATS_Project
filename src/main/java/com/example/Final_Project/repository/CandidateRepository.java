package com.example.Final_Project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Final_Project.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	  Optional<Candidate> findByEmail(String email); 

}