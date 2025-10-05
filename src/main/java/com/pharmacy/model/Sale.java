package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate saleDate;
    private double totalAmount;
    private String paymentType; // Cash, Card, etc.

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "sale_medicines", joinColumns = @JoinColumn(name = "sale_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicines;

    @ManyToOne
    @JoinColumn(name = "prescrption_id")
    private Prescription prescription;
}