package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {

    private final EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public Hospital findById(Long id) {
        System.out.println("repository id = " + id);
        return em.find(Hospital.class , id);
    }

    public List<Hospital> findByName(String hospitalName) {
        return em.createQuery("select m from Hospital m where m.hospitalName = :hospitalName", Hospital.class)
                .setParameter("hospitalName", hospitalName)
                .getResultList();
    }

    public List<Hospital> findAll() {
        return em.createQuery("select m from Hospital m", Hospital.class)
                .getResultList();
    }

    public void delete(Long hospitalId) {
        String jpql = "delete from Hospital m where m.hospitalId =:hospitalId";
        Query query = em.createQuery(jpql).setParameter("hospitalId", hospitalId);
        query.executeUpdate();
    }

    public int findAllCount() {
        return ((Number) em.createQuery("select count(hospitalId) from Hospital ")
                .getSingleResult()).intValue();
    }

    public List<Hospital> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select m from Hospital m", Hospital.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
