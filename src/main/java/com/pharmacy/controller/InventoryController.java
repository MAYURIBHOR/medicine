package com.pharmacy.controller;

import com.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventoryController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "inventory";
    }
}
