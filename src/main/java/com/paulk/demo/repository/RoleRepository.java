package com.paulk.demo.repository;

import com.paulk.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.Optional;

/**
 * A {@link JpaRepository} which encapsulates a {@link Role} {@link Entity}.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
