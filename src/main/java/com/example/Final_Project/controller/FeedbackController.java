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

import com.example.Final_Project.entity.Feedback;
import com.example.Final_Project.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // ✅ Create feedback
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

    // ✅ Get all feedback
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    // ✅ Get feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        return feedback.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Feedback not found with ID: " + id));
    }

    // ✅ Update feedback
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long id, @RequestBody Feedback updatedFeedback) {
        Feedback updated = feedbackService.updateFeedback(id, updatedFeedback);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(404).body("Feedback not found for update with ID: " + id);
    }

    // ✅ Delete feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        boolean deleted = feedbackService.deleteFeedback(id);
        if (deleted) {
            return ResponseEntity.ok("Feedback deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Feedback not found with ID: " + id);
        }
    }

    // ✅ Get feedback by interview ID
    @GetMapping("/interview/{interviewId}")
    public ResponseEntity<?> getFeedbackByInterviewId(@PathVariable Long interviewId) {
        Optional<Feedback> feedback = feedbackService.getFeedbackByInterviewId(interviewId);
        return feedback.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Feedback not found for Interview ID: " + interviewId));
    }
}