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

    private static final int LOW_STOCK_THRESHOLD = 5;

    @Autowired
    private AlertRepository alertRepository;

    /**
     * Save an alert
     */
    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    /**
     * Get all alerts
     */
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    /**
     * Check a medicine and generate expiry or low-stock alerts
     */
    public void checkAndGenerateAlert(Medicine medicine) {
        if (medicine == null)
            return;

        LocalDate today = LocalDate.now();

        // Check for expiry
        if (medicine.getExpiryDate() != null && medicine.getExpiryDate().isBefore(today)) {
            createAlertIfNotExists(medicine, "EXPIRY",
                    "Medicine " + medicine.getName() + " has expired on " + medicine.getExpiryDate());
        }

        // Check for low stock
        if (medicine.getStock() <= LOW_STOCK_THRESHOLD) {
            createAlertIfNotExists(medicine, "STOCK_LOW",
                    "Medicine " + medicine.getName() + " is low in stock (" + medicine.getStock() + ").");
        }
    }

    /**
     * Create alert if not already existing
     */
    private void createAlertIfNotExists(Medicine medicine, String type, String message) {
        Optional<Alert> existing = alertRepository.findFirstByMedicineAndType(medicine, type);

        if (existing.isPresent())
            return; // skip duplicates

        Alert alert = Alert.builder()
                .medicine(medicine)
                .type(type)
                .message(message)
                .createdAt(LocalDateTime.now())
                .sent(false)
                .build();

        alertRepository.save(alert);
    }
}
