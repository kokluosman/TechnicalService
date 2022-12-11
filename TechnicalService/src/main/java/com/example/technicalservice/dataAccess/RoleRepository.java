package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
