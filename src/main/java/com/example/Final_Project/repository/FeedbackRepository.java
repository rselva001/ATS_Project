package com.example.Final_Project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Final_Project.entity.Feedback;

public interface FeedbackRepository extends JpaRepository< Feedback, Long>{
	 Optional<Feedback> findByInterviewId(Long interviewId);
	 

}