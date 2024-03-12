package com.ritam.courses.repository;

import com.ritam.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByDivisionName(String divisionName);
    Optional<Course> findByIdAndDivisionId(Long id, Long division);
    @Query("SELECT c FROM Course c WHERE c.division.id = :divisionId")
    List<Course> findByDivisionId(Long divisionId);
}
