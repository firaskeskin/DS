package com.example.user_service.model;
import jakarta.persistence.*;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String specialization;

    @Enumerated(EnumType.STRING)
    private Role role = Role.INSTRUCTOR; // حقل الدور
    public enum Role {
        INSTRUCTOR
    }


    public Instructor() {}

    public Instructor(String name, String email, String password, String specialization) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}

