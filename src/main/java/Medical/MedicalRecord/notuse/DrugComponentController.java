//package Medical.MedicalRecord.notuse;
//
//import Medical.MedicalRecord.domain.DrugComponent;
//import Medical.MedicalRecord.repository.DrugComponentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/drugs")
//public class DrugComponentController {
//
//    private DrugComponentRepository drugComponentRepository;
//
//    /**
//     * 생성
//     */
//    @PostMapping
//    public ResponseEntity<?> saveDrugComponent(@RequestBody DrugComponent drugComponent){
//        DrugComponent saveDrugComponent = drugComponentRepository.save(drugComponent);
//        return ResponseEntity.ok(saveDrugComponent);
//    }
//
//    /**
//     * 전체 병원 목록 조회
//     */
//    @GetMapping("/list")
//    public List<DrugComponent> getDrugComponentList(){
//        List<DrugComponent> drugComponentList = drugComponentRepository.findAll();
//        return drugComponentList;
//    }
//
//    /**
//     * 아이디로 조회
//     */
//    @GetMapping("/{componentId}")
//    public DrugComponent getDrugComponent(@PathVariable("componentId") Long id) {
//        return drugComponentRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("조회하는 성분명이 없습니다"));
//    }
//
//    /**
//     * 수정
//     */
//    @PutMapping("/{componentId}")
//    public void updateHopitalInfo(@PathVariable("componentId") Long id, @RequestBody DrugComponent newDrugComponent) {
//        drugComponentRepository.findById(id)
//                .map(drugComponent -> {
//                    drugComponent.setComponentName(newDrugComponent.getComponentName());
//                    return drugComponentRepository.save(drugComponent);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("조회하는 성분명이 없습니다"));
//    }
//
//    /**
//     * 삭제
//     */
//    @DeleteMapping("/{componentId}")
//    public void deleteDrugComponent(@PathVariable("componentId") Long id){
//        drugComponentRepository.deleteById(id);
//    }
//}
