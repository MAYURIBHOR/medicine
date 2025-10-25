package com.pharmacy.service;

import com.pharmacy.model.Alert;
import com.pharmacy.model.Medicine;
import com.pharmacy.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private static final int DEFAULT_LOW_STOCK_THRESHOLD = 5;

    @Autowired
    private AlertRepository alertRepository;

    // Save alert
    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    // Get all alerts
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // Get only active alerts for dashboard
    public List<Alert> getActiveAlerts() {
        return alertRepository.findBySentFalse();
    }

    // Generate alert for medicine
    public void checkAndGenerateAlert(Medicine medicine) {
        if (medicine == null)
            return;

        LocalDate today = LocalDate.now();

        // EXPIRY alert
        if (medicine.getExpiryDate() != null && medicine.getExpiryDate().isBefore(today)) {
            createAlertIfNotExists(medicine, "EXPIRY",
                    "Medicine " + medicine.getName() + " has expired on " + medicine.getExpiryDate());
        }

        // LOW STOCK alert
        Integer stock = medicine.getStock();
        if (stock != null && stock <= DEFAULT_LOW_STOCK_THRESHOLD) {
            createAlertIfNotExists(medicine, "STOCK_LOW",
                    "Medicine " + medicine.getName() + " is low (stock: " + stock + ").");
        }
    }

    // Helper: create alert if not exists
    private void createAlertIfNotExists(Medicine medicine, String type, String message) {
        Optional<Alert> existing = alertRepository.findByMedicineAndType(medicine, type);
        if (existing.isPresent())
            return;

        Alert alert = Alert.builder()
                .type(type)
                .message(message)
                .createdAt(LocalDateTime.now())
                .sent(false)
                .medicine(medicine)
                .build();

        alertRepository.save(alert);
    }
}
