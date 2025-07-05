package com.example.user_service.service;

import com.example.user_service.model.Admin;
import com.example.user_service.model.Instructor;
import com.example.user_service.repository.AdminRepository;
import com.example.user_service.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.user_service.repository.InstructorRepository;

@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final InstructorRepository instructorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, InstructorRepository instructorRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.instructorRepository = instructorRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> login(String email, String password) {
        // أولًا نحاول البحث في جدول المدرسين
        Instructor instructor = instructorRepository.findByEmail(email).orElse(null);
        if (instructor != null && password.equals(instructor.getPassword())) {
            String token = jwtUtil.createToken(email, "ROLE_INSTRUCTOR", instructor.getId()); // يمكن تضمين الدور لاحقًا
            return ResponseEntity.ok("Bearer " + token);
        }

        // إذا لم يكن مدرس، نبحث في جدول الإداريين
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null && password.equals(admin.getPassword())) {
            String token = jwtUtil.createToken(email, "ROLE_ADMIN", null); // يمكن تضمين الدور لاحقًا
            return ResponseEntity.ok("Bearer " + token);
        }

        // إذا لم نجد المستخدم أو كلمة المرور خاطئة
        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
