package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // STOCK_LOW, EXPIRY, PRESCRIPTION_REFILL
    private String message;
    private LocalDateTime createdAt;
    private boolean sent;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
