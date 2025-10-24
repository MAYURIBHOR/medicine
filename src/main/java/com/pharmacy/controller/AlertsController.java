package com.pharmacy.controller;

import com.pharmacy.model.Alert;
import com.pharmacy.service.AlertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlertsController {

    private final AlertService alertService;

    public AlertsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/alerts")
    public String alertsPage(Model model) {
        try {
            List<Alert> alerts = alertService.getAllAlerts();
            model.addAttribute("alerts", alerts);
            model.addAttribute("pageTitle", "Alerts & Notifications");
        } catch (Exception e) {
            model.addAttribute("alerts", List.of());
            model.addAttribute("errorMessage", "Failed to load alerts.");
        }
        return "alerts";
    }
}
