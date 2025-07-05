package com.example.user_service.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.user_service.repository.InstructorRepository;
import com.example.user_service.dto.InstructorDTO;
import com.example.user_service.model.Instructor;
import org.springframework.http.ResponseEntity;
import com.example.user_service.service.InstructorService;
import java.util.Optional;





import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    private InstructorRepository instructorRepository;

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        Instructor savedInstructor = instructorService.saveInstructor(instructor);
        return ResponseEntity.ok(savedInstructor);
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}