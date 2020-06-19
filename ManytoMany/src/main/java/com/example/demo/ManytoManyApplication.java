package com.example.demo;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Book;
import com.example.demo.model.BookRepository;
import com.example.demo.model.Publisher;

@SpringBootApplication
public class ManytoManyApplication  {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManytoManyApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) { // Create a couple of Book and
	 * Publisher HashSet s = new HashSet(); s.add(new Publisher("Publisher A"));
	 * s.add(new Publisher("Publisher B")); bookRepository.save(new
	 * Book("Book 1",s)); }
	 */

}
