package com.ritam.courses.service;

import com.ritam.courses.model.Course;
import com.ritam.courses.model.Division;
import com.ritam.courses.repository.CourseRepository;
import com.ritam.courses.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    public List<Course> getCoursesByDivision(Long division) {
        return courseRepository.findByDivisionId(division);
    }

    public List<Course> getAllCoursesByDivision(String divisionName) {
        return courseRepository.findAllByDivisionName(divisionName);
    }

    public Course getCourseByIdAndDivision(Long division, Long id) {
        return courseRepository.findByIdAndDivisionId(id, division).orElse(null);
    }

    public Course createCourse(Long division, Course course) {
        if (Objects.nonNull(division) && Objects.nonNull(course)) {
            Optional<Division> div = divisionRepository.findById(division);
            if (div.isPresent()) {
                course.setDivision(div.get());
                return courseRepository.save(course);
            }
        }
        return null; // Failed to create course
    }

    public Course updateCourse(Long division, Long id, Course course) {
        if (Objects.nonNull(division) && Objects.nonNull(id) && Objects.nonNull(course)) {
            Course existingCourse = courseRepository.findByIdAndDivisionId(id, division).orElse(null);
            if (Objects.nonNull(existingCourse)) {
                existingCourse.setName(course.getName());
                existingCourse.setDuration(course.getDuration());
                existingCourse.setUndergraduate(course.getUndergraduate());
                existingCourse.setEgLevel(course.getEgLevel());
                existingCourse.setBfCycle(course.getBfCycle());
                return courseRepository.save(existingCourse);
            }
        }
        return null; // Course not found or update failed
    }

    public void deleteCourse(Long division, Long id) {
        if (Objects.nonNull(division) && Objects.nonNull(id)) {
            Course existingCourse = courseRepository.findByIdAndDivisionId(id, division).orElse(null);
            if (Objects.nonNull(existingCourse)) {
                courseRepository.delete(existingCourse);
            }
        }
    }
}
