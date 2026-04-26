package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}