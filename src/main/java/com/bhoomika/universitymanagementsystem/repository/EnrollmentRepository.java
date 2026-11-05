package com.bhoomika.universitymanagementsystem.repository;

import com.bhoomika.universitymanagementsystem.model.Enrollment;
import com.bhoomika.universitymanagementsystem.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
}
