package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Task(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
    }
    @Column(name = "title")
    private String title;


    @Column(name = "description")
    private String description;



    @Column(name = "status")
    private String status;
    public Task(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
    }


    @Column(name = "due_date")
    private Date dueDate;
    public Task(Date dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        Date now = new Date();
        if (dueDate.before(now)) {
            throw new IllegalArgumentException("Due date must be in the future.");

        }
    }

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;


    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}