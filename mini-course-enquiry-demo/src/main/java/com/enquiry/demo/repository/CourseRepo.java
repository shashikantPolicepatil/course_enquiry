package com.enquiry.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enquiry.demo.entity.Courses;

public interface CourseRepo extends JpaRepository<Courses, Integer> {

}
