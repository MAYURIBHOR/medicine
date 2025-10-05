package com.pharmacy.service;

import com.pharmacy.model.Prescription;
import com.pharmacy.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public List<Prescription> getPrescriptionsByCustomerId(Long customerId) {
        return prescriptionRepository.findByCustomerId(customerId);
    }

    public Prescription getPrescription(Long id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    /**
     * FIX: The missing delete method required by the controller.
     */
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
