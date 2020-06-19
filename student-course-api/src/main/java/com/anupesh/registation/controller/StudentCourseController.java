package com.anupesh.registation.controller;

import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anupesh.registation.exception.RecordNotFoundException;
import com.anupesh.registation.model.Course;
import com.anupesh.registation.model.Student;
import com.anupesh.registation.service.CourseService;
import com.anupesh.registation.service.StudentService;

@RestController
public class StudentCourseController {
	private final static Logger LOG = LoggerFactory.getLogger(StudentCourseController.class);

	private StudentService studentService;
	private CourseService courseService;

	@Autowired
	public StudentCourseController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) throws RecordNotFoundException {
		Student updated = studentService.addStudent(student).get();
		return new ResponseEntity<Student>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<String> removeStudent(Long studentId) {
		studentService.removeStudent(studentId);
		return ResponseEntity.status(HttpStatus.OK).body("Student is deleted of " + String.valueOf(studentId));
	}

	@PostMapping("/course")
	public ResponseEntity<String> addCourse(@Valid @RequestBody Course course) {
		LOG.info("Course  ::Course Name {}", course.getCourseName());
		courseService.addCourse(course);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Course with Name:" + course.getCourseName() + " has been Added.");
	}

	@DeleteMapping("/course/{courseId}")
	public ResponseEntity<String> removeCourse(Long courseId) {
		courseService.removeCourse(courseId);
		return ResponseEntity.status(HttpStatus.OK).body("Course with Id:" + courseId + " has been removed.");
	}

	@PutMapping("/registerStudentsToCourse/{courseId}")
	public ResponseEntity<String> enrollStudentToCourse(@PathVariable Long courseId,
			@RequestBody Set<Student> students) {
		courseService.registerStudentToCourse(courseId, students);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Students has been successfully Enrolled to Course :: " + courseId);
	}
	
	@PutMapping("/AssignCoursesToStudents/{courseId}")
	public ResponseEntity<String> enrollCourseToStudents(@PathVariable Long courseId,
			@RequestBody Set<Student> students) {
		courseService.registerStudentToCourse(courseId, students);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Students has been successfully Enrolled to Course :: " + courseId);
	}

	@GetMapping("/studentsByCourseName/{courseName}")
	public ResponseEntity<Set<Student>> getStudentsByCourseName(@PathVariable String courseName) {
		return new ResponseEntity<Set<Student>>(studentService.getStudentsByCourseName(courseName), new HttpHeaders(),
				HttpStatus.OK);
	}
	
	@GetMapping("/coursesByStudentName/{studentId}")
	public ResponseEntity<Set<Course>> getCoursesByStudentId(@PathVariable Long studentId) {
		return new ResponseEntity<Set<Course>>(studentService.getCoursesByStudentId(studentId), new HttpHeaders(),
				HttpStatus.OK);
	}

}
