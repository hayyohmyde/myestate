package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByUsernameOrEmail(String username, String Email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
