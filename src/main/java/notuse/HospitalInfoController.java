//package Medical.MedicalRecord.controller;
//
//import Medical.MedicalRecord.domain.Hospital;
//import Medical.MedicalRecord.repository.HospitalInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/hospitals")
//public class HospitalInfoController {
//
//    @Autowired
//    private HospitalInfoRepository hospitalInfoRepository;
//
//    /**
//     * 생성
//     */
//    @PostMapping
//    public ResponseEntity<?> saveMember(@RequestBody Hospital hospital){
//        Hospital saveHospital = hospitalInfoRepository.save(hospital);
//        return ResponseEntity.ok(saveHospital);
//    }
//
//    /**
//     * 전체 병원 목록 조회
//     */
//    @GetMapping("/list")
//    public List<Hospital> getHospitalList(){
//        List<Hospital> hospitalList = hospitalInfoRepository.findAll();
//        return hospitalList;
//    }
//
//    /**
//     * 아이디로 조회
//     */
//    @GetMapping("/{hospitalId}")
//    public Hospital getHospital(@PathVariable("hospitalId") Long id) {
//        return  hospitalInfoRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("조회하는 아이디가 없습니다"));
//    }
//
//    /**
//     * 수정
//     */
//    @PutMapping("/{hospitalId}")
//    public void updateHopitalInfo(@PathVariable("hospitalId") Long id, @RequestBody Hospital newHospital) {
//        hospitalInfoRepository.findById(id)
//                .map(hospital -> {
//                    hospital.setHospitalName(newHospital.getHospitalName());
//                    hospital.setHospitalAddress(newHospital.getHospitalAddress());
//                    hospital.setHospitalContact(newHospital.getHospitalContact());
//                    return hospitalInfoRepository.save(hospital);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("조회하는 병원 없습니다"));
//    }
//
//    /**
//     * 삭제
//     */
//    @DeleteMapping("/{hospitalId}")
//    public void deleteMember(@PathVariable("hospitalId") Long id){
//        hospitalInfoRepository.deleteById(id);
//    }
//}
