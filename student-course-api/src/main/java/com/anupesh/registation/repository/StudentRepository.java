package com.anupesh.registation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anupesh.registation.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
