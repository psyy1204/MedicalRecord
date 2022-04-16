package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.domain.Symptom;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.repository.SymptomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * 기록입력
     */
    @Transactional
    public Long add(Symptom symptom) {

        symptomRepository.save(symptom);
        return symptom.getSymptomId();
    }

    @Transactional
    public void addSymptomToRecord(Long recordId, Symptom symptom) {
        MedicalRecord findRecord = medicalRecordRepository.findById(recordId);
        findRecord.setHasSymptom(true);
        findRecord.setSymptom(symptom);
    }


    /**
     * 전체 조회
     */
    public List<Symptom> findAll() {
        return symptomRepository.findAll();
    }

    /**
     * 아이디로 조회
     */
    public Symptom findById(Long id) {
        return symptomRepository.findById(id);
    }

    /**
     * 정보 수정
     */
    @Transactional
    public void editSymptom(Long symptomId, String simpleSymptom, String detailSymptom, Date startDate,
                            Integer bodyTemperature, Integer pulse, Integer bloodPressure,
                            Integer oxygenSaturation){
        Symptom newSymptom = symptomRepository.findById(symptomId);
        newSymptom.setSimpleSymptom(simpleSymptom);
        newSymptom.setDetailSymptom(detailSymptom);
        newSymptom.setStartDate(startDate);
        newSymptom.setBloodPressure(bloodPressure);
        newSymptom.setBodyTemperature(bodyTemperature);
        newSymptom.setPulse(pulse);
        newSymptom.setOxygenSaturation(oxygenSaturation);
        newSymptom.setUpdatedDate(LocalDateTime.now());
    }

    /**
     * 삭제
     */
    @Transactional
    public void deleteSymptom(Long symptomId) {
        symptomRepository.deleteSymptom(symptomId);
    }

}
