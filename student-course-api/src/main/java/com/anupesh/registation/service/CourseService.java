package com.anupesh.registation.service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupesh.registation.exception.StudentCourseIllegalStateException;
import com.anupesh.registation.model.Course;
import com.anupesh.registation.model.Student;
import com.anupesh.registation.repository.CourseRepository;

@Service
public class CourseService {
	private final static Logger LOG = LoggerFactory.getLogger(CourseService.class);

	private CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public Long addCourse(Course course) {
		course = courseRepository.save(course);
		LOG.info("Course: {} has been successfully added. ", course.getCourseId());
		return course.getCourseId();
	}

	public void removeCourse(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);
		if (!course.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to remove Course. Invalid CourseId :: " + courseId);
		}
		courseRepository.delete(course.get());
	}

	@Transactional
	public void registerStudentToCourse(Long courseId, Set<Student> students) {
		LOG.info("CourseId :: {} , Student :: {}", courseId, students);
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (!courseOptional.isPresent()) {
			throw new StudentCourseIllegalStateException("Failed to register Student. Invalid CourseId :: " + courseId);
		}
		Course course = courseOptional.get();
		students.addAll(course.getStudents());
		course.setStudents(students);
		courseRepository.saveAndFlush(course);
	}

	public Optional<Course> getCourseByCourseName(String courseName) {
		return courseRepository.findCourseByCourseName(courseName);
	}
	
}
