//package com.example.springcrudmvc.controller;
//
//import com.example.springcrudmvc.model.Employee;
//
//import com.example.springcrudmvc.service.EmployeeService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
//
//@RestController
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/api/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService service;
//    
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    
//    @PostMapping("/send-email")
//    public ResponseEntity<String> sendEmail(
//        @RequestParam String email,
//        @RequestParam String subject,
//        @RequestParam String message
//    ) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//            helper.setTo(email);
//            helper.setSubject(subject);
//            helper.setText(message, true); // true for HTML content
//
//            mailSender.send(mimeMessage);
//            return ResponseEntity.ok("Email sent successfully to: " + email);
//        } catch (MessagingException e) {
//            return ResponseEntity.status(500).body("Error while sending email: " + e.getMessage());
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Employee>> getAllEmployees(
//        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
//        @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder,
//        @RequestParam(value = "keyword", required = false) String keyword
//    ) {
//        return ResponseEntity.ok(service.getEmployees(sortBy, sortOrder, keyword));
//    }
//    
//    
// 
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
//        Employee employee = service.getEmployeeById(id);
//        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
//        return ResponseEntity.ok(service.saveEmployee(employee));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
//        employee.setId(id);
//        return ResponseEntity.ok(service.saveEmployee(employee));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
//        service.deleteEmployee(id);
//        return ResponseEntity.noContent().build();
//    }
//}


package com.example.springcrudmvc.controller;

import com.example.springcrudmvc.model.Employee;


import com.example.springcrudmvc.service.EmployeeService;
//import com.example.springcrudmvc.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    
    
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendEmail/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id") Long employeeId) {
        try {
            // Fetch employee details by ID
            Employee employee = service.getEmployeeById(employeeId);
            if (employee == null) {
                return ResponseEntity.badRequest().body("Employee not found.");
            }

            String emailContent = String.format(
                    "Employee Details:\nID: %d\nName: %s\nEmail: %s\nSalary: %.2f",
                    employee.getId(), employee.getName(), employee.getEmail(), employee.getSalary()
            );

            // Create and send email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(employee.getEmail());
            helper.setSubject("Employee Details");
            helper.setText(emailContent);

            mailSender.send(message);
            return ResponseEntity.ok("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while sending the email.");
        }
    }

    
    

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder,
            @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.ok(service.getEmployees(sortBy, sortOrder, keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = service.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.saveEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return ResponseEntity.ok(service.saveEmployee(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}


