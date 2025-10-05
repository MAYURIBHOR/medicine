package com.pharmacy.service;

import com.pharmacy.model.Sale; // Assuming you have this model
import com.pharmacy.repository.SaleRepository; // Assuming you have this repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    /**
     * FIX: Adds the missing read method required by the controller.
     */
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // You will need this later for the save/update functionality
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // ... add more methods later (e.g., getSaleById, deleteSale)
}
