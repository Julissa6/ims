package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.model.AuditLog;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {
}