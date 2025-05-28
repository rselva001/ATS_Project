package com.example.Final_Project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Final_Project.entity.Feedback;
import com.example.Final_Project.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // ✅ Create new feedback
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // ✅ Get all feedbacks
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // ✅ Get feedback by ID
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    // ✅ Update feedback
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Optional<Feedback> existingOpt = feedbackRepository.findById(id);
        if (existingOpt.isPresent()) {
            Feedback existing = existingOpt.get();
            existing.setComments(updatedFeedback.getComments());
            existing.setRating(updatedFeedback.getRating());
            existing.setInterview(updatedFeedback.getInterview());
            return feedbackRepository.save(existing);
        }
        return null;
    }

    // ✅ Delete feedback
    public boolean deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Get feedback by interview ID
    public Optional<Feedback> getFeedbackByInterviewId(Long interviewId) {
        return feedbackRepository.findByInterviewId(interviewId);
    }
}