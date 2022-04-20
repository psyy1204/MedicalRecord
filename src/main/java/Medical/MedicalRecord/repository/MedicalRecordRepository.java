package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedicalRecordRepository {

    private final EntityManager em;
    //+
    private final HospitalRepository hospitalRepository;

    public void save(MedicalRecord medicalRecord) {
        em.persist(medicalRecord);
    }

    public MedicalRecord findById(Long id) {
        return em.find(MedicalRecord.class, id);
    }

    public List<MedicalRecord> findAll(){
        //조회타입
        return em.createQuery("select m from MedicalRecord m", MedicalRecord.class)
                //jpql-> sql(테이블 대상으로 쿼리)과 비슷 약간다름: 엔티티 객체를 대상으로 쿼리
                .getResultList();
    }

    public void deleteRecord(Long medicalRecordId) {
        String jpql = "delete from MedicalRecord m where m.recordId =:medicalRecordId";
        Query query = em.createQuery(jpql).setParameter("medicalRecordId", medicalRecordId);
        query.executeUpdate();
    }

}
