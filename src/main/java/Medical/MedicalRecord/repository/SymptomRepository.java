package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Symptom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SymptomRepository {

    private final EntityManager em;

    public void save(Symptom symptom) {
        em.persist(symptom);
    }

    public Symptom findById(Long id) {
        return em.find(Symptom.class, id);
    }

    public List<Symptom> findAll(){
        return em.createQuery("select m from Symptom m", Symptom.class)
                .getResultList();
    }

    public void deleteSymptom(Long symptomId) {
        String jpql = "delete from Symptom m where m.symptomId =:symptomId";
        Query query = em.createQuery(jpql).setParameter("symptomId", symptomId);
        query.executeUpdate();
    }

    public int findAllCount() {
        return ((Number) em.createQuery("select count(symptomId) from Symptom ")
                .getSingleResult()).intValue();
    }

    public List<Symptom> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select m from Symptom m order by m.symptomId desc", Symptom.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
