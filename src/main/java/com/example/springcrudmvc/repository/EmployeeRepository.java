package com.example.springcrudmvc.repository;

import com.example.springcrudmvc.model.Employee;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByNameContainingIgnoreCase(String keyword , Sort sort);
}
