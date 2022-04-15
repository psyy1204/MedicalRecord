//package Medical.MedicalRecord;
//
//import Medical.MedicalRecord.domain.Hospital;
//import Medical.MedicalRecord.domain.MedicalDepartmentCode;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    public List<MedicalDepartmentCode> findAll(){
//        return initService.findAll();
//    }
//
//    @PostConstruct
//    public void init() {
//        initService.dbInit();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//        private final EntityManager em;
//
//        public void dbInit() {
//            em.persist(new MedicalDepartmentCode("IM", "내과"));
//            em.persist(new MedicalDepartmentCode("GS", "일반외과"));
//            em.persist(new MedicalDepartmentCode("RM", "재활의학과"));
//            em.persist(new MedicalDepartmentCode("OS", "정형외과"));
//            em.persist(new MedicalDepartmentCode("ER", "응급의학과"));
//            em.persist(new MedicalDepartmentCode("NE", "신경과"));
//            em.persist(new MedicalDepartmentCode("NS", "신경외과"));
//            em.persist(new MedicalDepartmentCode("UR", "비뇨기과"));
//            em.persist(new MedicalDepartmentCode("FM", "가정의학과"));
//            em.persist(new MedicalDepartmentCode("DT", "치과"));
//            em.persist(new MedicalDepartmentCode("OB", "산부인과"));
//            em.persist(new MedicalDepartmentCode("NP", "정신과"));
//            em.persist(new MedicalDepartmentCode("OT", "안과"));
//            em.persist(new MedicalDepartmentCode("ENT", "이비인후과"));
//            em.persist(new MedicalDepartmentCode("PD", "소아과"));
//            em.persist(new MedicalDepartmentCode("ETC", "기타"));
//        }
//
//        public List<MedicalDepartmentCode> findAll() {
//            return em.createQuery("select m from MedicalDepartmentCode m", MedicalDepartmentCode.class)
//                    .getResultList();
//        }
//
//    }
//}
//
