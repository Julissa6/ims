package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}