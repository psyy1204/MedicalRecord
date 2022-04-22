package Medical.MedicalRecord.repository;

import Medical.MedicalRecord.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public List<Member> findByNickName(String nickName) {
        return em.createQuery("select m from Member m where m.nickName = :nickName", Member.class)
                .setParameter("nickName", nickName)
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

    public int findAllCount() {
        return ((Number) em.createQuery("select count(memberId) from Member ")
                .getSingleResult()).intValue();
    }

    public List<Member> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select m from Member m order by m.memberId desc ", Member.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

}
