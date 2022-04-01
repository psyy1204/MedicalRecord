package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.DrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugInfoRepository extends JpaRepository<DrugInfo, Long> {
}
