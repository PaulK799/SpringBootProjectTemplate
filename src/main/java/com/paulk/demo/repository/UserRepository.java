package com.paulk.demo.repository;

import com.paulk.demo.domain.model.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.Optional;

/**
 * A {@link JpaRepository} which encapsulates a {@link User} {@link Entity}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
