package com.example.user_service.service;

import com.example.user_service.model.Instructor;
import com.example.user_service.model.Student;
import com.example.user_service.repository.InstructorRepository;
import com.example.user_service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;


@Service
public class AdminService {

    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public AdminService(InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    // إضافة مدرب
    public ResponseEntity<?> addInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
        return ResponseEntity.ok("Instructor added successfully");
    }

    // إضافة طالب
    public ResponseEntity<?> addStudent(Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok("Student added successfully");
    }
}



