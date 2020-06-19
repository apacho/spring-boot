package com.anupesh.registation.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = "students")
@Entity
@Getter
@Setter
public class Course implements Serializable {

	private static final long serialVersionUID = -3770142805983192214L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COURSE_ID", unique = true, nullable = false, length = 20)
	private Long courseId;
	
	@Column(name = "COURSE_NAME", unique = true, length = 50)
	@NotEmpty(message = "Please provide a courseName")
	private String courseName;

	@Column(name = "COURSE_DETAILS")
	private String courseDetails;

	@Column(name = "COURSE_DURATION")
	private String courseDuration;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = {
			@JoinColumn(name = "COURSE_ID", nullable = false, updatable = false) }, 
	inverseJoinColumns = {
			@JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false) })
	private Set<Student> students;

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDetails=" + courseDetails
				+ ", courseDuration=" + courseDuration + "]";
	}

}
