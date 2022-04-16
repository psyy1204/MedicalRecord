package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Drug;
import Medical.MedicalRecord.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DrugService {

    private final DrugRepository drugRepository;

    /**
     * 약등록
     */
    @Transactional
    public Long add(Drug drug) {

        validateDuplicateMember(drug); // 중복확인
        drugRepository.save(drug);
        return drug.getDrugId();
    }

    /**
     * 중복 검사
     */
    private void validateDuplicateMember(Drug drug) {
        List<Drug> findDrugs = drugRepository.findByName(drug.getDrugName());
        if(!findDrugs.isEmpty()){
            throw new IllegalStateException("이미 존재하는 약 입니다.");
        }
    }

    /**
     * 전체 조회
     */
    public List<Drug> findAll() {
        return drugRepository.findAll();
    }

    /**
     * 아이디로 조회
     */
    public Drug findById(Long id) {
        return drugRepository.findById(id);
    }


    /**
     * 정보 수정
     */
    @Transactional
    public void edit(Long drugId, String drugName){
        Drug newDrug = drugRepository.findById(drugId);
        newDrug.setDrugName(drugName);
    }

    /**
     * 삭제
     */
    @Transactional
    public void delete(Long drugId) {
        drugRepository.delete(drugId);
    }

}