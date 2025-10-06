package com.pharmacy.controller;

import com.pharmacy.service.AlertService;
import com.pharmacy.model.Alert; // Import the model
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Collections;

@Controller
public class AlertsController {

    private final AlertService alertService;

    // Use constructor injection
    public AlertsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public String alerts(Model model) {
        try {
            // FIX: Call the generic method that exists in the simplified service
            List<Alert> allAlerts = alertService.getAllAlerts();

            // Note: If your HTML template relies on 'alerts', use that name.
            model.addAttribute("alerts", allAlerts);
            model.addAttribute("pageTitle", "Alerts & Notifications");

            return "alerts";

        } catch (Exception e) {
            System.err.println("Error fetching alerts data: " + e.getMessage());
            model.addAttribute("alerts", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load alert data.");
            return "alerts";
        }
    }
}