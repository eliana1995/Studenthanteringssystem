// repository/CourseRepository.java
package com.example.studentapp.repository;

import com.example.studentapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}

