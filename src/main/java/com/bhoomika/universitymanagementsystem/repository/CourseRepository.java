package com.bhoomika.universitymanagementsystem.repository;

import com.bhoomika.universitymanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
