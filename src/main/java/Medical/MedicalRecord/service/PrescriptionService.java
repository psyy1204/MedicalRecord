package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.domain.PrescriptionDrug;
import Medical.MedicalRecord.form.PrescriptionForm;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.repository.PrescriptionRepository;
import Medical.MedicalRecord.validation.PrescriptionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordRepository medicalRecordRepository;

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
     * 삭제
     */
    @Transactional
    public void deletePrescription(Long prescriptionId, Long recordId) {
        prescriptionRepository.delete(prescriptionId);
        if (prescriptionRepository.findRecordPrescription(recordId).isEmpty()){
            medicalRecordRepository.findById(recordId).setHasDrug(false);
        }
    }

    public void addRecordToPrescription(Long recordId, PrescriptionDrug prescriptionDrug) {
        MedicalRecord findRecord = medicalRecordRepository.findById(recordId);
        prescriptionDrug.setMedicalRecord(findRecord);


    }

    /**
     *
     * @param recordId
     * @return 레코드 아이디에 해당하는 처방약 리스트
     */
    public List<PrescriptionDrug> findRecordPrescription(Long recordId) {
        return prescriptionRepository.findRecordPrescription(recordId);
    }

    /**
     * 수정
     */
    @Transactional
    public void editPrescription(Long prescriptionId, Date drugStart,
                                 Date drugEnd, Integer dosesCount) {
        PrescriptionDrug newPrescription
                = prescriptionRepository.findById(prescriptionId);

        newPrescription.setDurationStart(drugStart);
        newPrescription.setDurationEnd(drugEnd);
        newPrescription.setDosesCount(dosesCount);
    }

    /**
     *
     * @param id
     * 수정(api용)
     */
    @Transactional
    public void updatePrescription(PrescriptionForm form, Long id) {
        if(form == null) return;
        PrescriptionDrug findPrescription = prescriptionRepository.findById(id);
        PrescriptionValidation prescriptionValidation = new PrescriptionValidation();
        prescriptionValidation.updatePrescription(findPrescription, form);

    }
}
