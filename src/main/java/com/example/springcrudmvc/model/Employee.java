package com.example.springcrudmvc.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

 //   @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
   
	  @Id
	    @SequenceGenerator(
	        name = "employee_sequence", 
	        sequenceName = "employee_sno_seq", // Matches the database sequence name
	        initialValue = 1, 
	        allocationSize = 1
	    )
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "salary")
    private Double salary;
    
    @Column(name = "add_date", updatable = false)
    private LocalDateTime addDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void onCreate() {
        this.addDate = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
   
}