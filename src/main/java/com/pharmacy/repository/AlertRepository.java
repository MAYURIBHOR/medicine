package main.java.com.pharmacy.repository;

import com.pharmacy.model.Alert;
import com.pharmacy.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findBySentFalse();

    boolean existsByMedicineAndTypeAndSentFalse(Medicine medicine, String type);

    List<Alert> findByTypeAndSentFalse(String type);
}