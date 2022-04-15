package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    /**
     * 병원등록
     */
    @Transactional
    public Long add(Hospital hospital) {

        validateDuplicateMember(hospital); // 중복확인
        hospitalRepository.save(hospital);
        return hospital.getHospitalId();
    }

    /**
     * 중복회원 검사
     */
    private void validateDuplicateMember(Hospital hospital) {
        List<Hospital> findMembers = hospitalRepository.findByName(hospital.getHospitalName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 병원입니다.");
        }
    }

    /**
     * 전체회원 조회
     */
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     */
    public Hospital findById(Long id) {
        return hospitalRepository.findById(id);
    }


    /**
     * 회원정보 수정
     */
    @Transactional
    public void edit(Long hospitalId, String hospitalName, String hospitalAddress, Integer hospitalContact){
        Hospital newHospital = hospitalRepository.findById(hospitalId);
        newHospital.setHospitalName(hospitalName);
        newHospital.setHospitalAddress(hospitalAddress);
        newHospital.setHospitalContact(hospitalContact);
    }

    /**
     * 삭제
     */
    @Transactional
    public void delete(Long hospitalId) {
        hospitalRepository.delete(hospitalId);
    }



}
