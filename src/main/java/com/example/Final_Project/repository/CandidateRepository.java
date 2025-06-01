package com.example.Final_Project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Final_Project.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	  Optional<Candidate> findByEmail(String email); 
	    List<Candidate> findByNameContainingIgnoreCase(String name);

	    

}