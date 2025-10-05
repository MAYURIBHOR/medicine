package com.pharmacy.service;

import com.pharmacy.model.Medicine;
import com.pharmacy.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // Get medicines with stock lower than threshold
    public List<Medicine> getLowStockMedicines(int threshold) {
        return medicineRepository.findByStockLessThan(threshold);
    }

    // Get medicines expired before given date
    public List<Medicine> getExpiredMedicines(LocalDate date) {
        return medicineRepository.findByExpiryDateBefore(date);
    }

    // Save or update medicine
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Get all medicines
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // Get medicine by ID
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
    }

    // Delete medicine by ID
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
}
