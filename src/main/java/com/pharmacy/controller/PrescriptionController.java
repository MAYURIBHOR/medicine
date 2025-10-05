package com.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrescriptionController {

    /**
     * Handles GET requests to /prescriptions and returns the prescriptions page.
     * Assumes the HTML template is named: prescriptions.html
     */
    @GetMapping("/prescriptions")
    public String viewPrescriptions(Model model) {
        // You would typically add service data to the model here:
        // model.addAttribute("prescriptions",
        // prescriptionService.getAllPrescriptions());

        return "prescriptions"; // Returns prescriptions.html
    }
}