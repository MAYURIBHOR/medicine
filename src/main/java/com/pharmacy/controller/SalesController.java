package com.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {

    /**
     * Handles GET requests to /sales and returns the sales page.
     */
    @GetMapping("/sales")
    public String viewSalesPage(Model model) {

        // This must match the name of your HTML file in templates (sales.html)
        return "sales";
    }
}
