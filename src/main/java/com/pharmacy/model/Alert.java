package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Customizer;

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
    private Medicine medicine;

    @ManyToOne
    private Customizer customer;
}
