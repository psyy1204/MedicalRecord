package Medical.MedicalRecord.unuse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalenderRepository {

    private final EntityManager em;

    public void save(calendar calendar) {
        em.persist(calendar);
    }

    public List<calendar> findAll() {
        return em.createQuery("select m from calendar m", calendar.class)
                .getResultList();
    }

}
