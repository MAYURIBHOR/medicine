package com.pharmacy.controller;

import com.pharmacy.model.Prescription;
import com.pharmacy.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    // Constructor Injection is best practice
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // --- READ (List all Prescriptions) ---

    /**
     * Handles GET requests to /prescriptions and returns the list page.
     */
    @GetMapping("/prescriptions")
    public String viewPrescriptions(Model model) {

        return "prescriptions"; //
    }

    // --- CREATE (Show Form) ---

    @GetMapping("/prescriptions/add")
    public String showAddForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("pageTitle", "Add New Prescription");
        return "prescription_form";
    }

    // --- EDIT (Show Form with Data) ---

    @GetMapping("/prescriptions/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Prescription prescription = prescriptionService.getPrescription(id);

        if (prescription == null) {
            ra.addFlashAttribute("errorMessage", "Prescription ID " + id + " not found!");
            return "redirect:/prescriptions";
        }

        model.addAttribute("prescription", prescription);
        model.addAttribute("pageTitle", "Edit Prescription (ID: " + id + ")");

        return "prescription_form";
    }

    // --- CREATE/UPDATE (Save Form Data) ---

    @PostMapping("/prescriptions/save")
    public String savePrescription(@ModelAttribute("prescription") Prescription prescription, RedirectAttributes ra) {
        try {
            prescriptionService.savePrescription(prescription);
            ra.addFlashAttribute("successMessage", "Prescription saved successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error saving prescription: " + e.getMessage());
            // Redirect back to the edit form on error, passing the ID
            return "redirect:/prescriptions/edit/" + (prescription.getId() != null ? prescription.getId() : "add");
        }
        return "redirect:/prescriptions";
    }

    // --- DELETE ---

    @GetMapping("/prescriptions/delete/{id}")
    public String deletePrescription(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            prescriptionService.deletePrescription(id);
            ra.addFlashAttribute("successMessage", "Prescription ID " + id + " deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error deleting prescription: " + e.getMessage());
        }
        return "redirect:/prescriptions";
    }
}