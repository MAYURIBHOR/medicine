package main.java.com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    private LocalDate expiryDate;

    @ManyToOne
    private Supplier supplier;
}