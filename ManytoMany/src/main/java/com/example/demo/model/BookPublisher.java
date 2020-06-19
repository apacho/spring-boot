
package com.example.demo.model;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.ApiOperation;

@RestController
public class BookPublisher {
	private final static Logger LOG = LoggerFactory.getLogger(BookPublisher.class);

	@Autowired
	BookRepository bookRepo;

	//@ApiOperation(value = "Add a employee")

	@PostMapping("/student")
	public ResponseEntity<Book> addStudent(@Valid @RequestBody Book book) throws Exception {
		Book updated = bookRepo.save(book);
		return new ResponseEntity<Book>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	

	
	@GetMapping("/student")
	public ResponseEntity<List<Book>> getStudentsByCourseName() {
		List<Book> books = bookRepo.findAll();
		//list.stream().filter(value -> value != null)
		return new ResponseEntity<List<Book>>(books, new HttpHeaders(), HttpStatus.OK);
	}

}
