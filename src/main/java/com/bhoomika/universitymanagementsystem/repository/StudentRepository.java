package com.bhoomika.universitymanagementsystem.repository;

import com.bhoomika.universitymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
