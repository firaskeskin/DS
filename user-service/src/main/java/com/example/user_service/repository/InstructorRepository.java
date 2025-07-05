package com.example.user_service.repository;
import com.example.user_service.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional <Instructor> findByEmail(String email);
}

