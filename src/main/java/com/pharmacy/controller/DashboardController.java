package com.pharmacy.controller;

import com.pharmacy.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Fetch active alerts (expired / low-stock medicines)
        model.addAttribute("alerts", alertService.getActiveAlerts());
        return "dashboard";
    }
}
