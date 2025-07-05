package com.example.user_service.model;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String major;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT; // حقل الدور
    public enum Role {
        STUDENT
    }
    // Constructors
    public Student() {}

    public Student(String name, String email, String password, String major) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.major = major;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getMajor() { return major; }

    public void setMajor(String major) { this.major = major; }
}

