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

    @Autowired
    private AlertService alertService;

    /**
     * Save or update a medicine and automatically generate alerts.
     */
    public Medicine saveMedicine(Medicine medicine) {
        Medicine savedMedicine = medicineRepository.save(medicine);
        alertService.checkAndGenerateAlert(savedMedicine); // Automatically generate alerts
        return savedMedicine;
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    public List<Medicine> getLowStockMedicines(int threshold) {
        return medicineRepository.findByStockLessThan(threshold);
    }

    public List<Medicine> getExpiredMedicines(LocalDate date) {
        return medicineRepository.findByExpiryDateBefore(date);
    }
}
