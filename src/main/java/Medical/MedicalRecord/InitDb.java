//package Medical.MedicalRecord;
//
//import Medical.MedicalRecord.domain.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    //빈이 다 올라오고 나면 스프링이 호출해주면서 초기화해준다
//    public void init() {
//        initService.initMember();
//    }
//
//    @RequiredArgsConstructor
//    @Component
//    @Transactional
//    static class InitService {
//
//        private final EntityManager em;
//
//        public void initMember() {
//            Member memberA = createMember("userA", "A", 20, "a@gmail.com");
//            Member memberB = createMember("userB", "B", 20, "b@gmail.com");
//            Member memberC = createMember("userC", "C", 20, "c@gmail.com");
//            Member memberD = createMember("userD", "D", 20, "d@gmail.com");
//            Member memberE = createMember("userE", "E", 20, "e@gmail.com");
//            Member memberF = createMember("userF", "F", 20, "f@gmail.com");
//            Member memberG = createMember("userG", "G", 20, "g@gmail.com");
//            Member memberH = createMember("userH", "H", 20, "h@gmail.com");
//
//            em.persist(memberA);
//            em.persist(memberB);
//            em.persist(memberC);
//            em.persist(memberD);
//            em.persist(memberE);
//            em.persist(memberF);
//            em.persist(memberG);
//            em.persist(memberH);
//        }
//
//        private Member createMember(String name, String nickName,Integer age, String email) {
//            Member member = new Member();
//            member.setUserName(name);
//            member.setNickName(nickName);
//            member.setAge(age);
//            member.setEmail(email);
//            return member;
//        }
//    }
//}
//
