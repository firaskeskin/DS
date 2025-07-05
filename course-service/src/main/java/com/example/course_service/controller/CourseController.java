package com.example.course_service.controller;

import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Optional;


import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course, @RequestParam Long instructorId) {
        // التحقق من أن المدرّب موجود فعليًا في قاعدة البيانات (تواصل مع user-service)
        if (!courseService.instructorExists(instructorId)) {
            return ResponseEntity.status(404).body("Instructor not found");
        }

        course.setInstructorId(instructorId);
        Course saved = courseService.createCourse(course);
        return ResponseEntity.ok(saved);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course course, @RequestParam Long instructorId) {
        Course existing = courseService.getCourseById(id).orElse(null);
        if (existing == null) return ResponseEntity.status(404).body("Course not found");

        if (!courseService.instructorExists(instructorId) || !existing.getInstructorId().equals(instructorId))
            return ResponseEntity.status(403).body("Only the course creator can update it or the instructor is not exist");

        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        return ResponseEntity.ok(courseService.updateCourse(existing));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id, @RequestParam Long instructorId) {
        Course existing = courseService.getCourseById(id).orElse(null);
        if (existing == null) return ResponseEntity.status(404).body("Course not found");

        if (!courseService.instructorExists(instructorId) || !existing.getInstructorId().equals(instructorId))
            return ResponseEntity.status(403).body("Only the course creator can delete it or the instructor is not exist");

        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted");
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<?> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<Course> courses = courseService.getCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
