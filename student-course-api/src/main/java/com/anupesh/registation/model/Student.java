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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

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

	//@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Course> courses;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + "]";
	}

}
