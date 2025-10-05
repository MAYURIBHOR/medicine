package com.pharmacy.controller;

import com.pharmacy.model.Sale;
import com.pharmacy.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // THIS IS NOW USED!

import java.util.Collections;
import java.util.List;

@Controller
public class SalesController {

    private final SaleService saleService;

    public SalesController(SaleService saleService) {
        this.saleService = saleService;
    }

    // --- 1. READ (List Recent Sales) ---
    @GetMapping("/sales")
    public String viewSalesPage(Model model) {
        try {
            // This is the read logic we fixed earlier
            List<Sale> recentSales = saleService.getAllSales();
            model.addAttribute("sales", recentSales);
        } catch (Exception e) {
            System.err.println("Error fetching sales data: " + e.getMessage());
            model.addAttribute("sales", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load sales data.");
        }
        return "sales";
    }

    // --- 2. START NEW SALE (Fixes /sales/new 404 error) ---
    @GetMapping("/sales/new")
    public String startNewSale(Model model) {
        // In a real app, this redirects to a complex Point-of-Sale (POS) form.
        model.addAttribute("sale", new Sale());
        model.addAttribute("pageTitle", "New Sale Transaction");
        // NOTE: You would need a pos_form.html or similar template
        return "sales_form";
    }

    // --- 3. VIEW SALES HISTORY (Fixes /sales/history 404 error) ---
    @GetMapping("/sales/history")
    public String viewSalesHistory(Model model) {
        try {
            // Assumes sales history is displayed on a separate, dedicated template
            model.addAttribute("salesHistory", saleService.getAllSales());
        } catch (Exception e) {
            model.addAttribute("salesHistory", Collections.emptyList());
            model.addAttribute("errorMessage", "Failed to load full sales history.");
        }
        return "sales_history";
    }

    // --- 4. SAVE SALE (Uses RedirectAttributes to send messages) ---
    @PostMapping("/sales/save")
    public String saveSale(@ModelAttribute("sale") Sale sale, RedirectAttributes ra) {
        try {
            saleService.saveSale(sale);
            // RedirectAttributes are used here to display a message on the next page
            // (/sales)
            ra.addFlashAttribute("successMessage", "Sale completed successfully! Invoice ID: " + sale.getId());
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error processing sale: " + e.getMessage());
        }
        return "redirect:/sales";
    }

    // You would add other CRUD methods (view/{id}, delete/{id}) here as well...
}