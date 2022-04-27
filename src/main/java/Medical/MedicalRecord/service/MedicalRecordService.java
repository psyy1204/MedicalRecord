package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.unuse.CalenderDTO;
import Medical.MedicalRecord.form.MedicalRecordForm;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.repository.MemberRepository;
import Medical.MedicalRecord.validation.MedicalRecordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final HospitalService hospitalService;
    private final MemberRepository memberRepository;

    /**
     * 기록 등록
     */
    @Transactional
    public Long add(MedicalRecord medicalRecord, Long memberId) {
        medicalRecord.setMember(memberRepository.findById(memberId));
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
                           Integer price, Long memberId,Date visitedDate, Date nextVisitedDate){
        MedicalRecord newMedicalRecord =medicalRecordRepository.findById(recordId);
        newMedicalRecord.setHospital(hospitalService.findHospital(hospitalName));
        newMedicalRecord.setDoctorName(doctorName);
        newMedicalRecord.setMedicalDepartmentCode(medicalDepartmentCode);
        newMedicalRecord.setEtc(etc);
        newMedicalRecord.setPrice(price);
        newMedicalRecord.setVisitedDate(visitedDate);
        newMedicalRecord.setMember(memberRepository.findById(memberId));
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

    /**
     * api patch용(일부수정)
     * @param id
     */
    @Transactional
    public void updateRecord(MedicalRecordForm form, Long id) {
        if(form == null) return;
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id);

        if(form.getHospitalName() != null) {
            Hospital hospital = hospitalService.findHospital(form.getHospitalName());
            medicalRecord.setHospital(hospital);
        }
        MedicalRecordValidation validation = new MedicalRecordValidation();
        validation.updateRecord(medicalRecord,form);
    }

    /**
     *
     * @param recordId
     * @return id에 해당하는 record
     * drug와 연관관계 확인
     */
    @Transactional
    public MedicalRecord addRecordToPrescription(Long recordId) {
        MedicalRecord findRecord = medicalRecordRepository.findById(recordId);
        findRecord.setHasDrug(true);
        return findRecord;
    }

    /**
     * @return 총개수
     */
    public int findAllCount() {
        return medicalRecordRepository.findAllCount();
    }

    /**
     * @param startIndex
     * @param pageSize
     * @return 페이징할 개수만큼 꺼내기기     */
    public List<MedicalRecord> findListPaging(int startIndex, int pageSize) {
        return medicalRecordRepository.findListPaging(startIndex, pageSize);
    }

    public List<Integer> countRecord() throws ParseException {
        //현재 년도
        List<Integer> countRecord = new ArrayList<>();
        //그 전년도까지의 데이터(20220101 이전까지 데이터)
        int lastCount = medicalRecordRepository.CountRecord(makeDate(1));

        for (int i = 2; i < 14; i++){
            int monthCount = medicalRecordRepository.CountRecord(makeDate(i));
            int count = monthCount - lastCount;
            if(count < 0) count = 0;
            countRecord.add(count);
            lastCount = monthCount;
        }
        return countRecord;
    }

    //월을 넣으면 20220101 날짜로 돌려줌
    private Date makeDate(int month) throws ParseException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        //13 들어오면 다음해의 첫월로 처리
        if (month == 13){
            year += 1;
            month = 1;
        }
        String day = "01";
        String mon;

        if(month < 10) {
            mon = "0" + Integer.toString(month);
        } else mon = Integer.toString(month);

        String date = Integer.toString(year)+ mon + day;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.parse(date);
    }

    public int totalPrice() {
        List<MedicalRecord> recordList = medicalRecordRepository.findAll();
        Integer price = 0;
        for (int i = 0; i < recordList.size(); i++) {
            if(recordList.get(i).getPrice()==null) price+=0;
            else price += recordList.get(i).getPrice();
        }
        return price;
    }
}
