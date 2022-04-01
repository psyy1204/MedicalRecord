package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.HospitalInfo;
import Medical.MedicalRecord.repository.HospitalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
public class HospitalInfoController {

    @Autowired
    private HospitalInfoRepository hospitalInfoRepository;

    /**
     * 생성
     */
    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody HospitalInfo hospitalInfo){
        HospitalInfo saveHospitalInfo = hospitalInfoRepository.save(hospitalInfo);
        return ResponseEntity.ok(saveHospitalInfo);
    }

    /**
     * 전체 병원 목록 조회
     */
    @GetMapping("/list")
    public List<HospitalInfo> getHospitalList(){
        List<HospitalInfo> hospitalInfoList = hospitalInfoRepository.findAll();
        return hospitalInfoList;
    }

    /**
     * 아이디로 조회
     */
    @GetMapping("/{hospitalId}")
    public HospitalInfo getHospital(@PathVariable("hospitalId") Long id) {
        return  hospitalInfoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("조회하는 아이디가 없습니다"));
    }

    /**
     * 수정
     */
    @PutMapping("/{hospitalId}")
    public void updateHopitalInfo(@PathVariable("hospitalId") Long id, @RequestBody HospitalInfo newHospitalInfo) {
        hospitalInfoRepository.findById(id)
                .map(hospitalInfo -> {
                    hospitalInfo.setHospitalName(newHospitalInfo.getHospitalName());
                    hospitalInfo.setHospitalAddress(newHospitalInfo.getHospitalAddress());
                    hospitalInfo.setHospitalContact(newHospitalInfo.getHospitalContact());
                    return hospitalInfoRepository.save(hospitalInfo);
                })
                .orElseThrow(() -> new IllegalArgumentException("조회하는 병원 없습니다"));
    }

    /**
     * 삭제
     */
    @DeleteMapping("/{hospitalId}")
    public void deleteMember(@PathVariable("hospitalId") Long id){
        hospitalInfoRepository.deleteById(id);
    }
}
