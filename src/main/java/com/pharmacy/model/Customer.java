package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Simple> sales;
}
