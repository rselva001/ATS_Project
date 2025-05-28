package com.example.Final_Project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Final_Project.entity.Application;
import com.example.Final_Project.repository.ApplicationRepository;
import com.example.Final_Project.repository.CandidateRepository;
import com.example.Final_Project.repository.JobRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    // ✅ CREATE a new application
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    // ✅ READ all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ✅ READ application by ID
    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    // ✅ UPDATE application
    public Application updateApplication(Long id, Application updatedApp) {
        Optional<Application> existingOpt = applicationRepository.findById(id);
        if (existingOpt.isPresent()) {
            Application existing = existingOpt.get();
            existing.setStatus(updatedApp.getStatus());
            existing.setJob(updatedApp.getJob());
            existing.setCandidate(updatedApp.getCandidate());
            return applicationRepository.save(existing);
        }
        return null;
    }

    // ✅ DELETE application
    public boolean deleteApplication(Long id) {
        if (applicationRepository.existsById(id)) {
            applicationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Get all applications by job ID
    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // ✅ Get all applications by candidate ID
    public List<Application> getApplicationsByCandidateId(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId);
    }
}