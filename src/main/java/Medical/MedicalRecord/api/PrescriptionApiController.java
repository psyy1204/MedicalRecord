package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Drug;
import Medical.MedicalRecord.domain.PrescriptionDrug;
import Medical.MedicalRecord.form.PrescriptionForm;
import Medical.MedicalRecord.service.DrugService;
import Medical.MedicalRecord.service.MedicalRecordService;
import Medical.MedicalRecord.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescriptions")
public class PrescriptionApiController {

    private final PrescriptionService prescriptionService;
    private final DrugService drugService;
    private final MedicalRecordService medicalRecordService;

    /**
     *
     * @param id
     * @return id에 해당하는 처방
     */
    @GetMapping("/{prescriptionId}")
    public PrescriptionDrug prescriptionDrug(@PathVariable("prescriptionId")Long id,
                                             HttpServletResponse response) throws IOException {
        PrescriptionDrug prescriptionDrug = prescriptionService.findById(id);
        if (prescriptionDrug == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"기록이 존재하지 않습니다.");
            return null;
        } else {
            return prescriptionDrug;
        }
    }

    /**
     * @param recordId
     * @return 기록에 해당하는 처방약 조회
     */
    @GetMapping("/list/{recordId}")
    public List<PrescriptionDrug> prescriptionList(@PathVariable Long recordId,
                                   Model model, HttpServletResponse response) throws IOException{
        List<PrescriptionDrug> prescriptionDrugs = prescriptionService.findRecordPrescription(recordId);
        if (prescriptionDrugs == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "기록이 존재하지 않습니다.");
            return null;
        } else {
            return prescriptionDrugs;
        }
    }

    /**
     * @param form
     * 처방기록 생성
     */
    @PostMapping
    public void addPrescription(@RequestBody PrescriptionForm form,
                                HttpServletResponse response) throws IOException{
        if(form.getDrugName() == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"기록이 존재하지 않습니다.");
        } else {

            PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
            prescriptionDrug.setDurationStart(form.getDrugStart());
            prescriptionDrug.setDurationEnd(form.getDrugEnd());
            prescriptionDrug.setDosesCount(form.getDosesCount());
            Drug findDrug = drugService.findByName(form.getDrugName());
            prescriptionDrug.setDrug(findDrug);
            prescriptionDrug.setMedicalRecord(medicalRecordService.addRecordToPrescription(form.getRecordId()));

            prescriptionService.add(prescriptionDrug);

        }
    }

    /**
     * @param id
     * 수정
     */
    @PatchMapping("/{prescriptionId}")
    public void editPrescription(@PathVariable("prescriptionId") Long id,
                                 @RequestBody PrescriptionForm form,
                                 HttpServletResponse response) throws IOException {
        if(prescriptionService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 처방이 없방니다.");
        } else{
            prescriptionService.updatePrescription(form, id);
        }
    }

    /**
     * @param id
     * 처방삭제
     */
    @DeleteMapping("/{prescriptionId}")
    public void deletePrescription(@PathVariable("prescriptionId") Long id,
                                   HttpServletResponse response) throws IOException{
        if(prescriptionService.findById(id)==null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 처방이 없습니다.");
        } else {
            prescriptionService.deletePrescription(id);
        }
    }
}
