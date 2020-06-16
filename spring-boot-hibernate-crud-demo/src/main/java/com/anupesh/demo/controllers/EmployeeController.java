package com.anupesh.demo.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupesh.demo.DemoApplication;
import com.anupesh.demo.exception.RecordNotFoundException;
import com.anupesh.demo.model.Employee;
import com.anupesh.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author anupesh.p
 *
 */
@RestController
@Api(value = "EmployeeController", description = "REST Apis related to Employee Entity!!!!")
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService service;
	private static final Logger logger=LoggerFactory.getLogger(EmployeeController.class);

	@ApiOperation(value = "View a list of available employees", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = service.getAllEmployees();
		logger.info("getAllEmployees");
		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	

	/**
	 * @param groupby
	 * @return
	 */
	@GetMapping("/groupby")
	public ResponseEntity<List<Employee>> getAllEmployeesByUsingGroupBy(@RequestParam("groupby") String groupby) {
		List<Employee> list = service.countEmployeeWithGroupBy(groupby);
		logger.info("getAllEmployees using groupby");
		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/paging")
	public ResponseEntity<List<Employee>> getAllEmployeesByPaging(
			@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		List<Employee> list = service.getAllEmployeesWithPagination(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}


	@ApiOperation(value = "Search a employee with an ID", response = Employee.class)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Employee entity = service.getEmployeeById(id);
		logger.info("Search a employee with an ID");
		return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a employee")
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee)
			throws RecordNotFoundException {
		Employee updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update a employee")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
			@RequestBody Employee entity) throws RecordNotFoundException {
		Employee saveEntity = service.getEmployeeById(id);
		saveEntity.setEmail(entity.getEmail());
		saveEntity.setEmployeeName(entity.getEmployeeName());
		saveEntity.setNickName(entity.getNickName());
		Employee updated = service.createOrUpdateEmployee(saveEntity);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a employee")
	@DeleteMapping("/{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException {
		service.deleteEmployeeById(Long.parseLong(id));
		return HttpStatus.FORBIDDEN;
	}
	
	
	@ApiOperation(value = "Search a employee with an ID", response = Employee.class)
	@GetMapping("/count")
	public ResponseEntity<String> getEmployeeCount() throws RecordNotFoundException {
		
		return ResponseEntity.status(HttpStatus.OK).body("Total count " + service.countEmployee());
	}

}