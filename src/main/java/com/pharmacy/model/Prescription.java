package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;
    private LocalDate dateIssued;
    private String medicineList; // Comma-separated list of medicines
    private String dosageInstructions;

    // Linking Prescription to Customer
    @ManyToOne
    @JoinColumn(name = "customer_id") // This column stores the selected customer
    private Customer customer;
}
