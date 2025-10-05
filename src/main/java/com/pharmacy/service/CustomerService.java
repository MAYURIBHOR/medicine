package com.pharmacy.service;

import com.pharmacy.model.Customer;
import com.pharmacy.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    // Note: Field injection is used here, matching your existing style.
    // Constructor injection is often preferred but this works perfectly.
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Saves a new customer or updates an existing one.
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves all customers from the database.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a single customer by their ID.
     */
    public Customer getCustomer(Long id) {
        // FindById returns an Optional, use .orElse(null) for convenience
        return customerRepository.findById(id).orElse(null);
    }

    /**
     * FIX: Adds the missing delete method required by the CustomerController.
     * Delegates the delete operation to the JpaRepository.
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
