package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // not necessary because JPARepository has implemented repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "SELECT a FROM apartments a WHERE " +
            "a.address.city LIKE CONCAT('%', :query, '%')" +
            "Or a.amount LIKE CONCAT('%', :query, '%')" +
            "Or a.apartmentType LIKE CONCAT('%', :query, '%')" +
            "Or a.noOfBedrooms LIKE CONCAT('%', :query, '%')" +
            "Or a.rent LIKE CONCAT('%', :query, '%')" +
            "Or a.estate LIKE CONCAT('%', :query, '%')" +
            "Or a.lga LIKE CONCAT('%', :query, '%')" +
            "Or a.country LIKE CONCAT('%', :query, '%')",
            nativeQuery = true
    )
    List<Apartment> searchApartments(String query);
}
