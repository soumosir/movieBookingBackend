package com.soumosir.bookmydream.repo;

import com.soumosir.bookmydream.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
