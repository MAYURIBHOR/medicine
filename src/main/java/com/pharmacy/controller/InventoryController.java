package com.pharmacy.controller;

import com.pharmacy.model.Medicine; // Make sure you import your Medicine Entity
import com.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Import this
import org.springframework.web.bind.annotation.PostMapping; // Import this

@Controller
public class InventoryController {

    @Autowired
    private MedicineService medicineService;

    // ... (Your existing @GetMapping("/inventory") method is here)

    // 1. HANDLER TO DISPLAY THE FORM (GET /inventory/add)
    @GetMapping("/inventory/add")
    public String showAddMedicineForm(Model model) {
        // Creates a new, empty Medicine object to bind the form fields to
        model.addAttribute("medicine", new Medicine());

        // Assumes your form template is named 'add_medicine.html'
        return "add_medicine";
    }

    // 2. HANDLER TO SAVE THE DATA (POST /inventory/save)
    @PostMapping("/inventory/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        // Service method to save the entity to the database
        medicineService.saveMedicine(medicine);

        // Redirects the user back to the main inventory page
        return "redirect:/inventory";
    }
}