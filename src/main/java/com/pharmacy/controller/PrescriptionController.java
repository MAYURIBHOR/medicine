package com.pharmacy.controller;

import com.pharmacy.model.Prescription;
import com.pharmacy.model.Customer;
import com.pharmacy.service.PrescriptionService;
import com.pharmacy.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final CustomerService customerService;

    public PrescriptionController(PrescriptionService prescriptionService, CustomerService customerService) {
        this.prescriptionService = prescriptionService;
        this.customerService = customerService;
    }

    @GetMapping("/prescriptions")
    public String viewPrescriptions(Model model) {
        try {
            List<Prescription> allPrescriptions = prescriptionService.getAllPrescriptions();
            model.addAttribute("prescriptions", allPrescriptions);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("prescriptions", Collections.emptyList());
            model.addAttribute("errorMessage", "Error fetching prescriptions.");
        }
        return "prescriptions";
    }

    @GetMapping("/prescriptions/add")
    public String showAddForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("pageTitle", "Add New Prescription");
        return "prescription_form";
    }

    @GetMapping("/prescriptions/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Prescription prescription = prescriptionService.getPrescription(id);
        if (prescription == null) {
            ra.addFlashAttribute("errorMessage", "Prescription not found.");
            return "redirect:/prescriptions";
        }
        model.addAttribute("prescription", prescription);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("pageTitle", "Edit Prescription (ID: " + id + ")");
        return "prescription_form";
    }

    @PostMapping("/prescriptions/save")
    public String savePrescription(@ModelAttribute("prescription") Prescription prescription, RedirectAttributes ra) {
        try {
            // Convert customer ID to actual Customer object
            if (prescription.getCustomer() != null && prescription.getCustomer().getId() != null) {
                Customer cust = customerService.getCustomer(prescription.getCustomer().getId());
                prescription.setCustomer(cust);
            } else {
                prescription.setCustomer(null);
            }

            prescriptionService.savePrescription(prescription);
            ra.addFlashAttribute("successMessage", "Prescription saved successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error saving prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }

    @GetMapping("/prescriptions/delete/{id}")
    public String deletePrescription(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            prescriptionService.deletePrescription(id);
            ra.addFlashAttribute("successMessage", "Prescription deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error deleting prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }
}
