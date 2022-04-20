//package Medical.MedicalRecord.unuse;
//
//import Medical.MedicalRecord.domain.DrugComponent;
//import Medical.MedicalRecord.repository.DrugComponentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class DrugComponentService {
//
//    private final DrugComponentRepository drugComponentRepository;
//    /**
//     * 약등록
//     */
//    @Transactional
//    public Long add(DrugComponent drugComponent) {
//
//        validateDuplicateMember(drugComponent); // 중복확인
//        drugComponentRepository.save(drugComponent);
//        return drugComponent.getComponentId();
//    }
//
//    /**
//     * 중복 검사
//     */
//    private void validateDuplicateMember(DrugComponent drugComponent) {
//        List<DrugComponent> findComponents = drugComponentRepository.findByName(drugComponent.getComponentName());
//        if(!findComponents.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 약 입니다.");
//        }
//    }
//
//    /**
//     * 전체 조회
//     */
//    public List<DrugComponent> findAll() {
//        return drugComponentRepository.findAll();
//    }
//
//    /**
//     * 아이디로 조회
//     */
//    public DrugComponent findById(Long id) {
//        return drugComponentRepository.findById(id);
//    }
//
//
//    /**
//     * 정보 수정
//     */
//    @Transactional
//    public void edit(Long componentId, String componentName){
//        DrugComponent newComponent = drugComponentRepository.findById(componentId);
//        newComponent.setComponentName(componentName);
//    }
//
//    /**
//     * 삭제
//     */
//    @Transactional
//    public void delete(Long drugId) {
//        drugComponentRepository.delete(drugId);
//    }
//}
