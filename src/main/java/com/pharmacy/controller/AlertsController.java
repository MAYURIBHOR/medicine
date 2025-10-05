package com.pharmacy.controller;

import com.pharmacy.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertsController {
    // ... @Autowired private AlertService alertService;

    @GetMapping("/alerts")
    public String alerts(Model model) {
        // model.addAttribute("alerts", alertService.getUnsentAlerts()); // <-- COMMENT
        // OUT THIS LINE
        return "alerts";
    }
}