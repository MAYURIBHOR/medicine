package com.pharmacy.model;

// *** REQUIRED JPA IMPORT ***
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// *** REQUIRED LOMBOK IMPORTS ***
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; // For @Data
import lombok.NoArgsConstructor;

@Entity // This is why you need the jakarta.persistence.Entity import
@Data // This is why you need the lombok.Data import
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

    // The temporary fix to isolate the crash:
    // @OneToMany(mappedBy = "customer")
    // private List<Sale> sales;
}