package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.form.MedicalRecordForm;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.service.HospitalService;
import Medical.MedicalRecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class MedicalRecordApiController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordRepository medicalRecordRepository;
    private final HospitalService hospitalService;

    /**
     * @return 모든 기록
     */
    @GetMapping
    public List<MedicalRecord> medicalRecords() {
        return medicalRecordRepository.findAll();
    }

    /**
     * @param id
     * @return id에 해당하는 medicalRecord
     */
    @GetMapping("/{recordId}")
    public MedicalRecord medicalRecord(@PathVariable("recordId") Long id,
                                       HttpServletResponse response) throws IOException {

        MedicalRecord medicalRecord = medicalRecordRepository.findById(id);
        if (medicalRecord == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"기록이 존재하지 않습니다.");
            return null;
        } else {
        return medicalRecord;
        }
    }

    /**
     * @param form
     * 진료기록 생성
     */
    @PostMapping
    public void addRecord(@RequestBody MedicalRecordForm form,
                          HttpServletResponse response) throws IOException{

        if(form.getDiagnosis() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"진단명은 필수입니다");
        } else {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDoctorName(form.getDoctorName());
            medicalRecord.setDiagnosis(form.getDiagnosis());
            medicalRecord.setMedicalDepartmentCode(form.getMedicalDepartmentCode());
            medicalRecord.setHospital(hospitalService.findHospital(form.getHospitalName()));
            medicalRecord.setEtc(form.getEtc());
            medicalRecord.setPrice(form.getPrice());
            medicalRecord.setVisitedDate(form.getVisitedDate());
            medicalRecord.setNextVisitDate(form.getNextVisitDate());
            medicalRecord.setCreatedDate(LocalDateTime.now());
            medicalRecord.setUpdatedDate(LocalDateTime.now());

            medicalRecordService.add(medicalRecord, form.getMemberId());
        }

    }

    /**
     * @param id
     * @param form(수정할 내용)
     * 내용 수정
     */
    @PatchMapping("/{recordId}")
    public void editRecord(@PathVariable("recordId") Long id,
                                    @RequestBody MedicalRecordForm form,
                                    HttpServletResponse response) throws IOException {
        if (medicalRecordService.findById(id) == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"기록이 존재하지 않습니다");
        } else {
            medicalRecordService.updateRecord(form, id);
        }
    }

    /**
     * @param id
     * id에 해당하는 record 삭제
     */
    @DeleteMapping("/{memberId}")
    public void deleteRecord(@PathVariable("memberId") Long id,
                             HttpServletResponse response) throws IOException {
        if (medicalRecordService.findById(id) == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"기록이 존재하지 않습니다");
        } else {
            medicalRecordService.deleteRecord(id);
        }
    }


}
