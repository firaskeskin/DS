package com.example.user_service.controller;

import com.example.user_service.model.Admin;
import com.example.user_service.service.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.example.user_service.model.Instructor;
import com.example.user_service.model.Student;

@RestController
@RequestMapping("/users/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // إضافة مدرب جديد (Instructor)
    @PostMapping("/add-instructor")
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        return adminService.addInstructor(instructor);
    }

    // إضافة طالب جديد (Student)
    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        return adminService.addStudent(student);
    }
}
