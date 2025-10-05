package com.pharmacy.service;

import com.pharmacy.model.Supplier;
import com.pharmacy.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Fetch supplier by ID safely
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null); // return null if not found
    }
}
