package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.service.MedicineService;
import com.pharmacy.service.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InventoryController {

    private final MedicineService medicineService;
    private final SupplierService supplierService;

    public InventoryController(MedicineService medicineService, SupplierService supplierService) {
        this.medicineService = medicineService;
        this.supplierService = supplierService;
    }

    // Show all medicines
    @GetMapping("/inventory")
    public String viewInventory(Model model) {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "inventory";
    }

    // Show add form
    @GetMapping("/inventory/add")
    public String showAddMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        // If you want supplier dropdown in form:
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "add_medicine";
    }

    // Save medicine (create)
    @PostMapping("/inventory/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        // If form binds supplier.id only, resolve Supplier if necessary in service or
        // here.
        medicineService.saveMedicine(medicine);
        return "redirect:/inventory";
    }

    // Show edit form
    @GetMapping("/inventory/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id);
        if (medicine == null) {
            // handle not found (redirect or show message)
            return "redirect:/inventory";
        }
        model.addAttribute("medicine", medicine);
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "edit_medicine";
    }

    // Update medicine (reuse save)
    @PostMapping("/inventory/update")
    public String updateMedicine(@ModelAttribute("medicine") Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/inventory";
    }

    // Delete medicine
    @GetMapping("/inventory/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Long id) {
        medicineService.deleteMedicine(id);
        return "redirect:/inventory";
    }
}
