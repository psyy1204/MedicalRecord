package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository{

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        //조회타입
        return em.createQuery("select m from Member m", Member.class)
                //jpql-> sql(테이블 대상으로 쿼리)과 비슷 약간다름: 엔티티 객체를 대상으로 쿼리
                .getResultList();
    }

    public List<Member> findByName(String username) {
        return em.createQuery("select m from Member m where m.userName = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    public void deleteMember(Long memberId) {
        String jpql = "delete from Member m where m.memberId =:memberId";
        Query query = em.createQuery(jpql).setParameter("memberId", memberId);
        query.executeUpdate();
    }

    public void edit(Member member) {
        if(member.getMemberId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
    }

}
