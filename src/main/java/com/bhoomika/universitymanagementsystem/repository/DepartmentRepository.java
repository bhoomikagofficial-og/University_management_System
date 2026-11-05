package com.bhoomika.universitymanagementsystem.repository;

import com.bhoomika.universitymanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
