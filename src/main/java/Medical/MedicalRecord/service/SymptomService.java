package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.domain.Symptom;
import Medical.MedicalRecord.form.SymptomForm;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.repository.SymptomRepository;
import Medical.MedicalRecord.validation.SymptomValidation;
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
                            Float bodyTemperature, Integer pulse, Integer systolic, Integer diastolic,
                            Integer oxygenSaturation){
        Symptom newSymptom = symptomRepository.findById(symptomId);
        newSymptom.setSimpleSymptom(simpleSymptom);
        newSymptom.setDetailSymptom(detailSymptom);
        newSymptom.setStartDate(startDate);
        newSymptom.setSystolic(systolic);
        newSymptom.setDiastolic(diastolic);
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

    public void updateSymptom(SymptomForm form, Long id) {
        if(form == null) return;
        Symptom findSymptom = symptomRepository.findById(id);
        findSymptom.setUpdatedDate(LocalDateTime.now());
        SymptomValidation symptomValidation = new SymptomValidation();
        symptomValidation.updateSymptom(findSymptom, form);
    }

    public int findAllCount() {
        return symptomRepository.findAllCount();
    }

    public List<Symptom> findListPaging(int startIndex, int pageSize){
        return symptomRepository.findListPaging(startIndex,pageSize);
    }
}
