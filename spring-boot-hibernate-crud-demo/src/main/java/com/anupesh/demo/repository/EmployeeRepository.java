package com.anupesh.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anupesh.demo.model.Employee;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
	 @Query("SELECT COUNT(u) FROM Employee u")
	    Long countUsers();	
	 
	 @Query("select new map(count(v) as cnt, v.email) from Employee v group by :names")
	 public List<?> findEmpCount(@Param("names") String names);
	 
}
