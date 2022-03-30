//package Medical.MedicalRecord;
//
//import Medical.MedicalRecord.domain.Member;
//import Medical.MedicalRecord.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//import static java.time.LocalDateTime.*;
//
//@SpringBootTest
//public class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void SaveMember() {
//
//        Member member = Member.builder()
//                .userName("ss")
//                .updatedDate(now())
//                .createdDate(now())
//                .build();
//
//        memberRepository.save(member)
//    }
//}
