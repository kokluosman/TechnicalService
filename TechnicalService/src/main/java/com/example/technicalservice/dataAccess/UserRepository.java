package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    List<User> findByNameOrderByName(String name);
    List<User> findByEmailContainingIgnoreCase(String email);

}
