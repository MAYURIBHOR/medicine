package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;
    private String notes;
    private LocalDate dateIssued;

    // *** FIX: ADD THE MISSING STATUS FIELD ***
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}