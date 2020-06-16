package com.anupesh.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated employee ID")
    private Long id;
    
    @ApiModelProperty(notes = "The employee name")
    @NotEmpty(message = "employeeName must not be empty")
    @Column(name="employee_name")
    private String employeeName;
    
    @ApiModelProperty(notes = "The employee nick name")
    @Column(name="nick_name")
    private String nickName;
   
    @ApiModelProperty(notes = "The employee email")
    @Email(message = "email should be a valid email")
    @NotEmpty(message = "email must not be empty")
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", employeeName=" + employeeName + ", nickName=" + nickName + ", email="
				+ email + "]";
	}

	public Employee(Long id, @NotEmpty(message = "employeeName must not be empty") String employeeName, String nickName,
			@Email(message = "email should be a valid email") @NotEmpty(message = "email must not be empty") String email) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.nickName = nickName;
		this.email = email;
	}
	
	
	
	

}