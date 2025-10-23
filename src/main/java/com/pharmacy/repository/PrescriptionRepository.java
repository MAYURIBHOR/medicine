package com.pharmacy.repository;

import com.pharmacy.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    // Removed findByCustomerId since Prescription has no customerId
}
