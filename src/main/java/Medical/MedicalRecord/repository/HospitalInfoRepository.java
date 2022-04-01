package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.HospitalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalInfoRepository extends JpaRepository<HospitalInfo, Long> {
}
