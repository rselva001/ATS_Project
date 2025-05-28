package com.example.Final_Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Final_Project.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application , Long> {
	
	  List<Application> findByJobId(Long jobId);
	    List<Application> findByCandidateId(Long candidateId);

}