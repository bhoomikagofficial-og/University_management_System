package com.bhoomika.universitymanagementsystem.repository;

import com.bhoomika.universitymanagementsystem.model.Teaches;
import com.bhoomika.universitymanagementsystem.model.TeachesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachesRepository extends JpaRepository<Teaches, TeachesId> {
}
