package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Land;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Long> {
}
