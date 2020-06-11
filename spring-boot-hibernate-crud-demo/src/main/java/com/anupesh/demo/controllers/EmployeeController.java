package com.anupesh.demo.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anupesh.demo.exception.RecordNotFoundException;
import com.anupesh.demo.model.EmployeeEntity;
import com.anupesh.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "EmployeeController", description = "REST Apis related to Employee Entity!!!!")
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@ApiOperation(value = "View a list of available employees", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		List<EmployeeEntity> list = service.getAllEmployees();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Search a employee with an ID", response = EmployeeEntity.class)
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		EmployeeEntity entity = service.getEmployeeById(id);
		return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a employee")
	@PostMapping
	public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee)
			throws RecordNotFoundException {
		EmployeeEntity updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update a employee")
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable("id") Long id,
			@RequestBody EmployeeEntity entity) throws RecordNotFoundException {
		EmployeeEntity saveEntity = service.getEmployeeById(id);
		saveEntity.setEmail(entity.getEmail());
		saveEntity.setEmployeeName(entity.getEmployeeName());
		saveEntity.setNickName(entity.getNickName());
		EmployeeEntity updated = service.createOrUpdateEmployee(saveEntity);
		return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a employee")
	@DeleteMapping("/{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
		service.deleteEmployeeById(Long.parseLong(id));
		return HttpStatus.FORBIDDEN;
	}

}