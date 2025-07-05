package com.example.user_service.service;
import com.example.user_service.model.Instructor;
import com.example.user_service.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;



import java.util.List;

@Service
public class InstructorService {
    private InstructorRepository instructorRepository;

    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.orElse(null);
    }
}
