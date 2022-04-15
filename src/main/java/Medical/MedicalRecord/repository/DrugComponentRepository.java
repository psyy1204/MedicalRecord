package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.DrugComponent;
import Medical.MedicalRecord.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugComponentRepository {

    private final EntityManager em;

    public void save(DrugComponent drugComponent) {
        em.persist(drugComponent);
    }

    public DrugComponent findById(Long id) {
        return em.find(DrugComponent.class , id);
    }

    public List<DrugComponent> findByName(String componentName) {
        return em.createQuery("select m from DrugComponent m where m.componentName = :componentName", DrugComponent.class)
                .setParameter("componentName", componentName)
                .getResultList();
    }

    public List<DrugComponent> findAll() {
        return em.createQuery("select m from DrugComponent m", DrugComponent.class)
                .getResultList();
    }

    public void delete(Long componentId) {
        String jpql = "delete from DrugComponent m where m.componentId =:componentId";
        Query query = em.createQuery(jpql).setParameter("componentId", componentId);
        query.executeUpdate();
    }
}
