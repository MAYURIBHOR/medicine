package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // getName() works now
    private String batchNumber;
    private int stock;
    private double price;
    private LocalDate expiryDate; // getExpiryDate() works now

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier; // make sure you have a Supplier entity
}
