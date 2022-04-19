package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.form.MedicalRecordForm;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import Medical.MedicalRecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class MedicalRecordApiController {

    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * @return 모든 기록
     */
    @GetMapping
    public List<MedicalRecord> medicalRecords() {
        return medicalRecordRepository.findAllWithOthers();
    }

    /**
     * @param id
     * @return id에 해당하는 medicalRecord
     */
    @GetMapping("/{recordId}")
    public MedicalRecord medicalRecord(@PathVariable("recordId") Long id,
                                       HttpServletResponse response) throws IOException {

        MedicalRecord medicalRecord = medicalRecordRepository.findOneWithOthers(id);
        if (medicalRecord == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return medicalRecord;
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
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        medicalRecordService.updateRecord(form, id);
    }

    /**
     * @param id
     * id에 해당하는 record 삭제
     */
    @DeleteMapping("/{memberId}")
    public void deleteRecord(@PathVariable("memberId") Long id,
                             HttpServletResponse response) throws IOException {
        if (medicalRecordService.findById(id) == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        medicalRecordService.deleteRecord(id);
    }


}
