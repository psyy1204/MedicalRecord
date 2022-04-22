package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.form.HospitalForm;
import Medical.MedicalRecord.repository.HospitalRepository;
import Medical.MedicalRecord.validation.HospitalValidation;
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
     * 중복병원 검사
     */
    private void validateDuplicateMember(Hospital hospital) {
        List<Hospital> findMembers = hospitalRepository.findByName(hospital.getHospitalName());

        if (!findMembers.isEmpty()) {
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
        System.out.println("id = " + id);

        return hospitalRepository.findById(id);
    }


    /**
     * 회원정보 수정
     */
    @Transactional
    public void edit(Long hospitalId, String hospitalName, String hospitalAddress, Integer hospitalContact) {
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

    /**
     * 이름으로 검색 없으면 생성
     */
    @Transactional
    public Hospital findHospital(String hospitalName) {
        List<Hospital> hospitals = hospitalRepository.findByName(hospitalName);

        if (hospitals.isEmpty()) {
            Hospital hospital = new Hospital();
            hospital.setHospitalName(hospitalName);
            hospitalRepository.save(hospital);

            return hospital;
        } else {
            return hospitals.get(0);
        }
    }

    @Transactional
    public void updateHospital(HospitalForm form, Long id) {
        if (form != null) {
            Hospital findHospital = hospitalRepository.findById(id);
            HospitalValidation hospitalValidation = new HospitalValidation();
            hospitalValidation.updateHospital(findHospital, form);
        }
    }

    public int findAllCount() {
        return hospitalRepository.findAllCount();
    }

    public List<Hospital> findListPaging(int startIndex, int pageSize) {
        return hospitalRepository.findListPaging(startIndex, pageSize);
    }
}
