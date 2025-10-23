package com.pharmacy.controller;

import com.pharmacy.model.Alert;
import com.pharmacy.service.AlertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class AlertsController {

    private final AlertService alertService;

    public AlertsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public String alerts(Model model) {
        try {
            List<Alert> allAlerts = alertService.getAllAlerts();
            if (allAlerts == null)
                allAlerts = Collections.emptyList();

            model.addAttribute("alerts", allAlerts);
            model.addAttribute("pageTitle", "Alerts & Notifications");

            return "alerts";

        } catch (Exception e) {
            System.err.println("Error fetching alerts: " + e.getMessage());
            model.addAttribute("alerts", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load alerts data.");
            return "alerts";
        }
    }
}
