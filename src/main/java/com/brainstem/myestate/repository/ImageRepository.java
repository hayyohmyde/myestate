package com.brainstem.myestate.repository;

import com.brainstem.myestate.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
