package com.example.usermanagement.Repository;

import com.example.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
