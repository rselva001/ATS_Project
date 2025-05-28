package com.example.Final_Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Final_Project.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long>{

	 List<Interview> findByApplicationId(Long applicationId);
	    List<Interview> findByInterviewerId(Long interviewerId);
}