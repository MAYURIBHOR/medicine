package com.pharmacy.controller;

import com.pharmacy.model.Supplier;
import com.pharmacy.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // NEW IMPORT
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // NEW IMPORT

import java.util.List;

@Controller
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // --- READ (List all) ---

    /**
     * Handles GET requests to /suppliers and returns the suppliers list page.
     */
    @GetMapping("/suppliers")
    public String viewSuppliers(Model model) {
        try {
            List<Supplier> allSuppliers = supplierService.getAllSuppliers();
            model.addAttribute("suppliers", allSuppliers);
        } catch (Exception e) {
            model.addAttribute("suppliers", List.of());
            model.addAttribute("errorMessage", "Failed to load supplier data.");
        }
        return "suppliers";
    }

    // --- CREATE (Show Form) ---

    /**
     * Handles GET requests to /suppliers/add, prepares an empty Supplier for the
     * form.
     */
    @GetMapping("/suppliers/add")
    public String showAddForm(Model model) {
        // Initializes a new, empty Supplier object
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("pageTitle", "Add New Supplier");
        return "supplier_form";
    }

    // --- EDIT (Show Form with Data) ---

    /**
     * Handles GET requests to /suppliers/edit/{id}, fetches existing data for the
     * form.
     */
    @GetMapping("/suppliers/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Supplier supplier = supplierService.getSupplierById(id);

        if (supplier == null) {
            // Redirect with a message if supplier is not found
            ra.addFlashAttribute("errorMessage", "Supplier ID " + id + " not found!");
            return "redirect:/suppliers";
        }

        // Pass the existing Supplier object to the form
        model.addAttribute("supplier", supplier);
        model.addAttribute("pageTitle", "Edit Supplier (ID: " + id + ")");
        return "supplier_form";
    }

    // --- CREATE/UPDATE (Save Form Data) ---

    /**
     * Handles POST requests from the form (both New and Edit).
     * The service handles the persistence: if ID is present, it updates; otherwise,
     * it creates.
     */
    @PostMapping("/suppliers/save")
    public String saveSupplier(@ModelAttribute("supplier") Supplier supplier, RedirectAttributes ra) {
        try {
            supplierService.saveSupplier(supplier);
            ra.addFlashAttribute("successMessage", "Supplier saved successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error saving supplier: " + e.getMessage());
        }
        return "redirect:/suppliers";
    }

    // --- DELETE ---

    /**
     * Handles GET requests to /suppliers/delete/{id}.
     * NOTE: In a production app, delete should generally be a POST/DELETE request
     * for security.
     */
    @GetMapping("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            supplierService.deleteSupplier(id); // Assumes you add this method to the service
            ra.addFlashAttribute("successMessage", "Supplier ID " + id + " deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error deleting supplier: " + e.getMessage());
        }
        return "redirect:/suppliers";
    }
}