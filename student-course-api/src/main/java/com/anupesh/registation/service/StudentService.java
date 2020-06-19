package com.anupesh.registation.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupesh.registation.exception.StudentCourseIllegalStateException;
import com.anupesh.registation.model.Course;
import com.anupesh.registation.model.Student;
import com.anupesh.registation.repository.StudentRepository;

@Service
public class StudentService {
	private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);

	private StudentRepository studentRepository;
	private CourseService courseService;

	@Autowired
	public StudentService(CourseService courseService, StudentRepository studentRepository) {
		this.courseService = courseService;
		this.studentRepository = studentRepository;
	}

	public Optional<Student> addStudent(Student student) {
		
		try {
			student = studentRepository.save(student);
			LOG.info("Student {} Successfully added", student.getStudentId());
		}catch(Exception e) {
			throw new StudentCourseIllegalStateException("Failed to credate Student, pls use different mobile number : " + student);
		}
		
		return Optional.ofNullable(student);
	}

	public void removeStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (!student.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to remove Student. Invalid StudentId :: " + studentId);
		}
		studentRepository.delete(student.get());
	}

	public void registerCourse(Long studentId, Set<Course> courses) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (!studentOptional.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to register course. Invalid CourseId :: " + studentId);
		}
		Student student = studentOptional.get();
		courses.addAll(student.getCourses());
		student.setCourses(courses);
		studentRepository.save(student);
	}

	@Transactional
	public Set<Student> getStudentsByCourseName(String courseName) {
		Optional<Course> course = courseService.getCourseByCourseName(courseName);
		if (!course.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to get Students. Invalid courseName :: " + courseName);
		}
		Comparator<Student> studentByName = (Student student1, Student student2) -> student1.getStudentName()
				.compareTo(student2.getStudentName());
		TreeSet<Student> sortedStudents = new TreeSet<>(studentByName);

		Hibernate.initialize(course.get().getStudents());
		Set<Student> students = course.get().getStudents();
		students.forEach(student -> student.setCourses(null));
		sortedStudents.addAll(students);
		LOG.debug("Actual Students :: {} and Sorted Students by Name:: {}", students, sortedStudents);
		return sortedStudents;
	}
	
	@Transactional
	public Set<Course> getCoursesByStudentId(Long studentId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		
		if (!studentOptional.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to get student. Invalid studentId :: " + studentId);
		}
		
		Comparator<Course> courseByName = (Course course1, Course course2) -> course1.getCourseName()
				.compareTo(course2.getCourseName());
		TreeSet<Course> sortedCourses = new TreeSet<>(courseByName);

		Set<Course> courses = studentOptional.get().getCourses();
		//courses.forEach(course -> course.setStudents(null));
		sortedCourses.addAll(courses);
		LOG.debug("Actual Students :: {} and Sorted Students by Name:: {}", courses, sortedCourses);
		return sortedCourses;
	}
	
}
