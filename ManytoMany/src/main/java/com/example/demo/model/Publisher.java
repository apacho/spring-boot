package com.example.demo.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = "books")
@Getter
@Setter
@Entity
public class Publisher implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;

    private String name;

    @ManyToMany(mappedBy = "publishers")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Book> books = new HashSet<>();

    public Publisher(String name) {
        this.name = name;
    }
    
    public Publisher() {
    	super();
    }
}