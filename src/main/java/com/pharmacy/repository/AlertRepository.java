package com.pharmacy.repository;

import com.pharmacy.model.Alert;
import com.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    // Used by AlertService to prevent duplicate alerts
    Optional<Alert> findFirstByMedicineAndType(Medicine medicine, String type);
}
