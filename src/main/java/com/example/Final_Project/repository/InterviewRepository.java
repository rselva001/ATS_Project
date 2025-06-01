package com.example.Final_Project.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Final_Project.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long>{

	 List<Interview> findByApplicationId(Long applicationId);
	    List<Interview> findByInterviewerId(Long interviewerId);


//	    List<Interview> findByScheduledTimeAfterOrderByScheduledTimeAsc(LocalDateTime time);

//	    List<Interview> findByScheduledTimeAfter(LocalDateTime now);
	    @Query(value = "SELECT * FROM interviews WHERE scheduled_time > CURRENT_TIMESTAMP", nativeQuery = true)
	    List<Interview> findUpcomingNative();

	    @Query("SELECT i FROM Interview i WHERE i.scheduledTime > :now ORDER BY i.scheduledTime ASC")
	    List<Interview> findByScheduledTimeAfterNow(@Param("now") LocalDateTime now);

	    List<Interview> findByApplication_Id(Long applicationId);
	    }
