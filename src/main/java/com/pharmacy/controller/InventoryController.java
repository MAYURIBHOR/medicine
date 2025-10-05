package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.service.MedicineService;
import com.pharmacy.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

// NOTE: Since you are using java.time.LocalDate, the CustomDateEditor is often unnecessary
// if the input format is 'yyyy-MM-dd' (HTML standard). However, we include a simpler binding 
// method just in case, using the ISO standard.

@Controller
public class InventoryController {

    private final MedicineService medicineService;
    private final SupplierService supplierService;

    // Use constructor injection for all dependencies
    public InventoryController(MedicineService medicineService, SupplierService supplierService) {
        this.medicineService = medicineService;
        this.supplierService = supplierService;
    }

    // --- READ (List Inventory) ---

    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        try {
            model.addAttribute("medicines", medicineService.getAllMedicines());
            return "inventory"; // Renders inventory.html
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: Failed to load inventory data.");
            e.printStackTrace();
            model.addAttribute("medicines", Collections.emptyList());
            model.addAttribute("errorMessage", "Error loading inventory. Check server logs.");
            return "inventory";
        }
    }

    // --- CREATE/EDIT (Show Form) ---

    // Handles both GET /inventory/add and GET /inventory/edit/{id}
    @GetMapping({ "/inventory/add", "/inventory/edit/{id}" })
    public String showMedicineForm(@PathVariable(value = "id", required = false) Long id, Model model) {

        Medicine medicine = (id == null)
                ? new Medicine()
                : medicineService.getMedicineById(id);

        if (medicine == null) {
            // Should not happen if data integrity is maintained, but handles the null case
            return "redirect:/inventory";
        }

        model.addAttribute("medicine", medicine);
        model.addAttribute("pageTitle", (id == null) ? "Add New Medicine" : "Edit Medicine");

        // Pass the list of suppliers for the dropdown menu in the form
        model.addAttribute("suppliers", supplierService.getAllSuppliers());

        return "medicine_form"; // Renders medicine_form.html
    }

    // --- CREATE/UPDATE (Save Form Data) ---

    @PostMapping("/inventory/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine, RedirectAttributes ra) {

        try {
            // Note: Service should handle saving the medicine, including its linked
            // supplier object
            medicineService.saveMedicine(medicine);
            ra.addFlashAttribute("successMessage", "Medicine saved successfully!");
        } catch (Exception e) {
            System.err.println("Database error during medicine save:");
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Error saving medicine: " + e.getMessage());
            // Redirect back to the form or the list on failure
            return "redirect:/inventory";
        }

        return "redirect:/inventory";
    }

    // --- DELETE ---

    @GetMapping("/inventory/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            medicineService.deleteMedicine(id);
            ra.addFlashAttribute("successMessage", "Medicine ID " + id + " deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error deleting medicine: " + e.getMessage());
        }
        return "redirect:/inventory";
    }
}