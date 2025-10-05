package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InventoryController {

    @Autowired
    private MedicineService medicineService;

    // 1. Show all medicines in inventory
    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "inventory"; // inventory.html
    }

    // 2. Show Add Medicine Form
    @GetMapping("/inventory/add")
    public String showAddMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "add_medicine"; // add_medicine.html
    }

    // 3. Save New Medicine
    @PostMapping("/inventory/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/inventory";
    }

    // 4. Show Edit Form
    @GetMapping("/inventory/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        model.addAttribute("medicine", medicine);
        return "edit_medicine"; // edit_medicine.html
    }

    // 5. Update Medicine
    @PostMapping("/inventory/update")
    public String updateMedicine(@ModelAttribute("medicine") Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/inventory";
    }

    // 6. Delete Medicine
    @GetMapping("/inventory/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Long id) {
        medicineService.deleteMedicine(id);
        return "redirect:/inventory";
    }
}
