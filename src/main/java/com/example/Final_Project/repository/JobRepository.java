package com.example.Final_Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Final_Project.entity.Job;
import com.example.Final_Project.entity.JobStatus;

public interface JobRepository extends JpaRepository<Job, Long>{

	
	 List<Job> findByStatus(JobStatus status);
}