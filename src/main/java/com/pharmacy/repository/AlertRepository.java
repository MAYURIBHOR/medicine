package com.pharmacy.repository;

import com.pharmacy.model.Alert;
import com.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    // existing methods remain
    // new methods for active alerts and duplicate check
    List<Alert> findBySentFalse();

    Optional<Alert> findByMedicineAndType(Medicine medicine, String type);
}
