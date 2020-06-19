package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = "publishers")
@Getter
@Setter
@Entity
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;

	@Column(name = "name")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "book_publisher", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"), 
	inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "publisherId"))
	private Set<Publisher> publishers;

	/*
	 * public Book(String name, Publisher... publishers) { this.name = name;
	 * this.publishers = Stream.of(publishers).collect(Collectors.toSet());
	 * this.publishers.forEach(x -> x.getBooks().add(this)); }
	 */

	public Book(String name, Set<Publisher> publishers) {
		super();
		this.name = name;
		this.publishers = publishers;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
