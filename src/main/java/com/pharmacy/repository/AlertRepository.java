package com.pharmacy.repository;

import com.pharmacy.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    // Simple CRUD methods are inherited. Add custom derived queries here only if
    // needed.
    // Example: List<Alert> findBySentFalse();
}