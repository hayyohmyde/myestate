package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
