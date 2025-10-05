package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Updated fields for clarity and alignment with HTML/Repository needs
    private String contactPerson; // Matches: supplier.contactPerson
    private String phone; // Matches: supplier.phone
    private String email; // Matches: supplier.email
}
