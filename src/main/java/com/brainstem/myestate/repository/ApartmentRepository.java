package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // not necessary because JPARepository has implemented repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "SELECT * FROM apartments WHERE " +
            "city LIKE CONCAT('%', :query, '%') " +
            "OR state LIKE CONCAT('%', :query, '%') " +
            "OR country LIKE CONCAT('%', :query, '%') " +
            "OR amount LIKE CONCAT('%', :query, '%') " +
            "OR apartment_type LIKE CONCAT('%', :query, '%') " +
            "OR no_of_rooms LIKE CONCAT('%', :query, '%') " +
            "OR is_rent LIKE CONCAT('%', :query, '%') " +
            "OR estate LIKE CONCAT('%', :query, '%') " +
            "OR lga LIKE CONCAT('%', :query, '%') " +
            "OR info LIKE CONCAT('%', :query, '%')",
            nativeQuery = true
    )
    List<Apartment> searchApartments(String query);
}
