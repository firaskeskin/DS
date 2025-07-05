package com.example.course_service.service;

import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import com.example.course_service.dto.InstructorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // إنشاء دورة جديدة
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // تحديث دورة (مُرر لها الكورس بعد التحقق من الصلاحية)
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    // حذف دورة
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // استرجاع جميع الدورات
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // استرجاع دورة حسب ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // استرجاع دورات مدرب معيّن
    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    // التحقق من وجود المدرّب عبر user-service
    public boolean instructorExists(Long instructorId) {
        try {
            String url = "http://user-service/instructors" + instructorId;
            InstructorDTO instructor = restTemplate.getForObject(url, InstructorDTO.class);
            return instructor != null;
        } catch (Exception e) {
            return false;
        }
    }
}
