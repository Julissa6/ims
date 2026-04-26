package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}