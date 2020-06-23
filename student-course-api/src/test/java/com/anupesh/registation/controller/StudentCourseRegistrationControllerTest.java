package com.anupesh.registation.controller;


import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anupesh.registation.exception.RecordNotFoundException;
import com.anupesh.registation.model.Course;
import com.anupesh.registation.model.Student;
import com.anupesh.registation.service.CourseService;
import com.anupesh.registation.service.StudentService;

public class StudentCourseRegistrationControllerTest {
	private final static Logger LOG = LoggerFactory.getLogger(StudentCourseController.class);

	@Mock
	private StudentService studentServiceMock;

	@Mock
	private CourseService courseServiceMock;

	@InjectMocks
	private StudentCourseController studentCourseRegistrationController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddStudent() throws RecordNotFoundException {
		Student student = new Student();
		student.setStudentId(1l); //
		studentCourseRegistrationController.addStudent(student);
	}

	@Test
	public void testRemoveStudent() {
		Long studentId = 1l;
		studentCourseRegistrationController.removeStudent(studentId);
	}

	@Test
	public void testAddCourse() {
		Course course = new Course();
		course.setCourseId(1l);
		LOG.info("Course  ::Course Name {}", course.getCourseName());
		studentCourseRegistrationController.addCourse(course);
	}

	@Test
	public void testRemoveCourse() {
		Long courseId = 1l;
		studentCourseRegistrationController.removeCourse(courseId);
	}

	@Test
	public void testEnrollStudentToCourse() {
		Long courseId = 1l;
		Set<Student> students = Collections.emptySet();
		studentCourseRegistrationController.enrollStudentToCourse(courseId, students);
	}

	@Test
	public void testGetStudentsByCourseName() {
		String courseName = "DevOps";
		studentCourseRegistrationController.getStudentsByCourseName(courseName);
	}

}
