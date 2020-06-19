package com.anupesh.registation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anupesh.registation.model.Course;
import com.anupesh.registation.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Optional<Student> findByMobileNumber(String mobileNumber);

}
