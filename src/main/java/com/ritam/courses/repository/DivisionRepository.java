package com.ritam.courses.repository;

import com.ritam.courses.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
    Division findByName(String name);
}

