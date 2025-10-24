package com.pharmacy.controller;

import com.pharmacy.model.Medicine;
import com.pharmacy.service.AlertService;
import com.pharmacy.service.MedicineService;
import com.pharmacy.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String listMedicines(Model model) {
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "medicines";
    }

    @GetMapping("/add")
    public String addMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "add-medicine";
    }

    @GetMapping("/edit/{id}")
    public String editMedicineForm(@PathVariable Long id, Model model) {
        Medicine med = medicineService.getMedicineById(id);
        model.addAttribute("medicine", med);
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "add-medicine";
    }

    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        medicineService.saveMedicine(medicine); // Alerts handled inside service
        return "redirect:/medicines";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return "redirect:/medicines";
    }
}
