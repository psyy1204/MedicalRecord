package Medical.MedicalRecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//데이터베이스에 관련된 bean임을 명시하는 어노테이션
//JpaRepository<Entity타입, 기본키의 데이터타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    List<Member> findAll();

}
