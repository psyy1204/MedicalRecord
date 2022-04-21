package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.PrescriptionDrug;
import Medical.MedicalRecord.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    /**
     * 약추가
     */
    @Transactional
    public Long add(PrescriptionDrug prescriptionDrug) {

        prescriptionRepository.save(prescriptionDrug);
        return prescriptionDrug.getPrescriptionDrugId();
    }

    /**
     * 전체 조회
     */
    public List<PrescriptionDrug> findAll() {
        return prescriptionRepository.findAll();
    }

    /**
     * 아이디로 조회
     */
    public PrescriptionDrug findById(Long id) {
        return prescriptionRepository.findById(id);
    }

    /**
     * 정보 수정
     */
//    @Transactional
//    public void editPrescription(){
//        Member newMember = memberRepository.findById(memberId);
//        newMember.setAge(age);
//        newMember.setUserName(userName);
//        newMember.setNickName(nickName);
//        newMember.setGender(gender);
//        newMember.setHeight(height);
//        newMember.setWeight(weight);
//        newMember.setUpdatedDate(LocalDateTime.now());
//    }

    /**
     * 삭제
     */
    @Transactional
    public void deleteMember(Long prescriptionId) {
        prescriptionRepository.delete(prescriptionId);
    }

}
