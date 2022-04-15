package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Drug;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugRepository {

    private final EntityManager em;

    public void save(Drug drug) {
        em.persist(drug);
    }

    public Drug findById(Long id) {
        return em.find(Drug.class , id);
    }

    public List<Drug> findByName(String drugName) {
        return em.createQuery("select m from Drug m where m.drugName = :drugName", Drug.class)
                .setParameter("drugName", drugName)
                .getResultList();
    }

    public List<Drug> findAll() {
        return em.createQuery("select m from Hospital m", Drug.class)
                .getResultList();
    }

    public void delete(Long drugId) {
        String jpql = "delete from Drug m where m.drugId =:drugId";
        Query query = em.createQuery(jpql).setParameter("drugId", drugId);
        query.executeUpdate();
    }
}
