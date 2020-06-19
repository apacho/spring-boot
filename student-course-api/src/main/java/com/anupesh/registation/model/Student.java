package com.anupesh.registation.model;

import java.io.Serializable;
import java.util.HashSet;
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

@EqualsAndHashCode(exclude = "courses")
@Entity
@Getter
@Setter
public class Student implements Serializable {
	private static final long serialVersionUID = 1013479834262222490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENT_ID", unique = true, nullable = false, length = 20)
	private Long studentId;

	@Column(name = "STUDENT_NAME")
	@NotEmpty(message = "Please provide a studentName")
	private String studentName;

	@Column(name = "MOBILE_NUMBER" , unique= true, length = 30)
	@NotEmpty(message = "Please provide a mobileNumber")
	private String mobileNumber;

	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "COMPLETED")
	private Boolean completed;

	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students", cascade=CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = {
			@JoinColumn(name = "COURSE_ID", nullable = false, updatable = false) }, 
	inverseJoinColumns = {
			@JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false) })
	private Set<Course> courses = new HashSet<Course>();


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + "]";
	}

}
