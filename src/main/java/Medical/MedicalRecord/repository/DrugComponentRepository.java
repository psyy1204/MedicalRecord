package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.DrugComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugComponentRepository extends JpaRepository<DrugComponent, Long> {
}