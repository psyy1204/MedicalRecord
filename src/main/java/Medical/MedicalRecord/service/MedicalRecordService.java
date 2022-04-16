package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.repository.HospitalRepository;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final HospitalService hospitalService;

    /**
     * 기록 등록
     */
    @Transactional
    public Long add(MedicalRecord medicalRecord) {

        medicalRecordRepository.save(medicalRecord);
        return medicalRecord.getRecordId();
    }

    /**
     * 전체 조회
     */
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    /**
     * 아이디로 조회
     */
    public MedicalRecord findById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    /**
     * 수정
     */
    @Transactional
    public void editRecord(Long recordId, String doctorName, String hospitalName
                           ,String medicalDepartmentCode, String etc,
                           Integer price, Date visitedDate, Date nextVisitedDate){
        MedicalRecord newMedicalRecord =medicalRecordRepository.findById(recordId);
        newMedicalRecord.setHospital(hospitalService.findHospital(hospitalName));
        newMedicalRecord.setDoctorName(doctorName);
        newMedicalRecord.setMedicalDepartmentCode(medicalDepartmentCode);
        newMedicalRecord.setEtc(etc);
        newMedicalRecord.setPrice(price);
        newMedicalRecord.setPrice(price);
        newMedicalRecord.setPrice(price);
        newMedicalRecord.setVisitedDate(visitedDate);
        newMedicalRecord.setNextVisitDate(nextVisitedDate);
        newMedicalRecord.setUpdatedDate(LocalDateTime.now());
    }

    /**
     * 삭제
     */
    @Transactional
    public void deleteRecord(Long recordId) {
        medicalRecordRepository.deleteRecord(recordId);
    }

}
