package com.pharmacy.repository;

import com.pharmacy.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByCustomerId(Long customerId);
}
