package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String batchNumber;
    private int stock;
    private double price;

    // *** DEFINITIVE FIX: Use ISO standard format for HTML5 input type="date" ***
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
