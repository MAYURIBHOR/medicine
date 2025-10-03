package main.java.com.pharmacy.service;

import com.pharmacy.model.Alert;
import com.pharmacy.model.Medicine;
import com.pharmacy.repository.AlertRepository;
import com.pharmacy.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public List<Alert> getUnsentAlerts() {
        return alertRepository.findBySentFalse();
    }

    // Run every day at 8am
    @Scheduled(cron = "0 0 8 * * ?")
    public void generateExpiryAlerts() {
        List<Medicine> soonExpiring = medicineRepository.findByExpiryDateBefore(LocalDate.now().plusDays(30));
        for (Medicine med : soonExpiring) {
            if (!alertRepository.existsByMedicineAndTypeAndSentFalse(med, "EXPIRY")) {
                Alert alert = Alert.builder()
                        .type("EXPIRY")
                        .message("Medicine " + med.getName() + " is expiring soon (" + med.getExpiryDate() + ")")
                        .createdAt(LocalDateTime.now())
                        .medicine(med)
                        .sent(false)
                        .build();
                alertRepository.save(alert);
            }
        }
    }
}
