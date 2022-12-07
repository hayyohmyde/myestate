package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // not necessary because JPARepository has implemented repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
