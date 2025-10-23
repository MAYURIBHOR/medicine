package com.pharmacy.service;

import com.pharmacy.model.Alert;
import com.pharmacy.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
