package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.PrescriptionDrug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PrescriptionRepository {

    private final EntityManager em;

    public void save(PrescriptionDrug prescriptionDrug) {
        em.persist(prescriptionDrug);
    }

    public PrescriptionDrug findById(Long id) {
        return em.find(PrescriptionDrug.class , id);
    }

    public List<PrescriptionDrug> findAll() {
        return em.createQuery("select m from PrescriptionDrug m", PrescriptionDrug.class)
                .getResultList();
    }

    public void delete(Long prescriptionId) {
        String jpql = "delete from PrescriptionDrug m where m.prescriptionDrugId =:prescriptionId";
        Query query = em.createQuery(jpql).setParameter("prescriptionId", prescriptionId);
        query.executeUpdate();
    }

    public List<PrescriptionDrug> findRecordPrescription(Long recordId) {
        return em.createQuery("select m from PrescriptionDrug m where m.medicalRecord.recordId =: recordId")
                .setParameter("recordId", recordId)
                .getResultList();

    }
}
