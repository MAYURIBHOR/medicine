package com.pharmacy.controller;

import com.pharmacy.service.CustomerService;
import com.pharmacy.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    // Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // --- 1. READ (List all Customers) ---
    @GetMapping("/customers")
    public String viewCustomers(Model model) {
        try {
            List<Customer> allCustomers = customerService.getAllCustomers();
            model.addAttribute("customers", allCustomers);
        } catch (Exception e) {
            System.err.println("Error fetching customer data: " + e.getMessage());
            model.addAttribute("customers", List.of());
            model.addAttribute("errorMessage", "Failed to load customer data.");
        }
        return "customers";
    }

    // --- 2. CREATE (Show Form for New Customer) ---

    @GetMapping("/customers/add")
    public String showAddForm(Model model) {
        // This method handles the /customers/add link
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");
        return "customer_form"; // Returns customer_form.html
    }

    // --- 3. EDIT (Show Form with Existing Data) ---

    @GetMapping("/customers/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        Customer customer = customerService.getCustomer(id);

        if (customer == null) {
            ra.addFlashAttribute("errorMessage", "Customer ID " + id + " not found!");
            return "redirect:/customers";
        }

        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");
        return "customer_form";
    }

    // --- 4. CREATE/UPDATE (Save Form Data) ---

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes ra) {
        try {
            customerService.saveCustomer(customer);
            ra.addFlashAttribute("successMessage", "Customer saved successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error saving customer: " + e.getMessage());
        }
        return "redirect:/customers";
    }

    // --- 5. DELETE ---

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            customerService.deleteCustomer(id);
            ra.addFlashAttribute("successMessage", "Customer ID " + id + " deleted successfully!");
        } catch (Exception e) {
            ra.addFlashAttribute("errorMessage", "Error deleting customer: " + e.getMessage());
        }
        return "redirect:/customers";
    }
}