package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Apartment;
import com.brainstem.myestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByApartments(Apartment apartment);
    User findById(long id);
    Optional<User> findByEmail(String email);
    User findByEmailAndPassword(String email, String password);

}
