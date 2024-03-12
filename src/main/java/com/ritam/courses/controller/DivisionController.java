package com.ritam.courses.controller;

import com.ritam.courses.model.Course;
import com.ritam.courses.model.Division;
import com.ritam.courses.service.DivisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/divisions")
@Slf4j
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping
    public ResponseEntity<List<Division>> getAllDivisions() {
        List<Division> divisions = divisionService.getAllDivisions();
        return ResponseEntity.ok().body(divisions);
    }

    @GetMapping("/{division}")
    public ResponseEntity<List<Course>> getCoursesByDivision(@PathVariable Long division) {
        List<Course> courses = divisionService.getCoursesByDivision(division);
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{division}/courses/all")
    public ResponseEntity<List<Course>> getAllCoursesByDivision(@PathVariable String division) {
        List<Course> courses = divisionService.getAllCoursesByDivision(division);
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{division}/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long division, @PathVariable Long id) {
        Course course = divisionService.getCourseByIdAndDivision(division, id);
        if (Objects.nonNull(course)) {
            return ResponseEntity.ok().body(course);
        } else {
            log.error("Course with id {} not found in division {}", id, division);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{division}/courses")
    public ResponseEntity<Course> createCourse(@PathVariable Long division, @RequestBody Course course) {
        Course createdCourse = divisionService.createCourse(division, course);
        if (Objects.nonNull(createdCourse)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
        } else {
            log.error("Failed to create course in division {}", division);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{division}/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long division, @PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = divisionService.updateCourse(division, id, course);
        if (Objects.nonNull(updatedCourse)) {
            return ResponseEntity.ok().body(updatedCourse);
        } else {
            log.error("Failed to update course with id {} in division {}", id, division);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{division}/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long division, @PathVariable Long id) {
        divisionService.deleteCourse(division, id);
        log.info("Deleted course with id {} in division {}", id, division);
        return ResponseEntity.noContent().build();
    }
}
