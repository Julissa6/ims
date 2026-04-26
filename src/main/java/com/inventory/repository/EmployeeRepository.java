package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}