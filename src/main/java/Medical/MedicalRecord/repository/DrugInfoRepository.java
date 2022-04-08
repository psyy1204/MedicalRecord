package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugInfoRepository extends JpaRepository<Drug, Long> {
}
