//package com.example.springcrudmvc.service;

//
//import com.example.springcrudmvc.model.Employee;
//import com.example.springcrudmvc.repository.EmployeeRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository repository;
//
//    // Get all employees with optional sorting and keyword filtering
//    public List<Employee> getEmployees(String sortBy, String sortOrder, String keyword) {
//        Sort sort = (sortOrder.equalsIgnoreCase("desc")) 
//            ? Sort.by(sortBy).descending() 
//            : Sort.by(sortBy).ascending();
//
//        if (keyword != null && !keyword.isEmpty()) {
//            return repository.findByNameContainingIgnoreCase(keyword, sort);
//        }
//
//        return repository.findAll(sort);
//    }
//
//    // Get all employees without filtering or sorting
//    public List<Employee> getAllEmployees() {
//        return repository.findAll();
//    }
//
//    // Search employees by keyword (case-insensitive)
//    public List<Employee> searchEmployeesByName(String keyword) {
//        return repository.findByNameContainingIgnoreCase(keyword, Sort.unsorted());
//    }
//
//    // Save or update an employee
//    public Employee saveEmployee(Employee employee) {
//        return repository.save(employee);
//    }
//
//    // Delete an employee by ID
//    public void deleteEmployee(Long id) {
//        repository.deleteById(id);
//    }
//
//    // Get an employee by ID
//    public Employee getEmployeeById(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
//    }
//}








package com.example.springcrudmvc.service;

import com.example.springcrudmvc.model.Employee;

import com.example.springcrudmvc.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Get all employees with optional sorting and keyword filtering, including addDate and lastUpdate
    public List<Employee> getEmployees(String sortBy, String sortOrder, String keyword) {
        Sort sort = (sortOrder.equalsIgnoreCase("desc")) 
            ? Sort.by(sortBy).descending() 
            : Sort.by(sortBy).ascending();

        if (keyword != null && !keyword.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(keyword, sort);
        }

        return repository.findAll(sort);
    }

    // Get all employees without filtering or sorting
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Search employees by name (case-insensitive)
    public List<Employee> searchEmployeesByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword, Sort.unsorted());
    }

    // Save or update an employee, including addDate and lastUpdate
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    // Get employee by ID, including addDate and lastUpdate
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }
}
