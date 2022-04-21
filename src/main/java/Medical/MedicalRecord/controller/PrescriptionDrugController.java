package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Drug;
import Medical.MedicalRecord.domain.PrescriptionDrug;
import Medical.MedicalRecord.form.PrescriptionForm;
import Medical.MedicalRecord.service.DrugService;
import Medical.MedicalRecord.service.MedicalRecordService;
import Medical.MedicalRecord.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionDrugController {

    private final PrescriptionService prescriptionService;
    private final DrugService drugService;
    private final MedicalRecordService medicalRecordService;

    /**
     * 기록 등록폼
     */
    @GetMapping("/new")
    public String addForm(@RequestParam(value = "recordId", required = false) Long recordId , Model model) {
        PrescriptionForm prescriptionForm = new PrescriptionForm();
        prescriptionForm.setRecordId(recordId);
        model.addAttribute("prescriptionForm", prescriptionForm);
        return "prescriptions/addForm";
    }

    /**
     * 기록 등록
     */
    @PostMapping("/new")
    public String addPrescription(@Valid PrescriptionForm form, BindingResult result,
                             RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "prescriptions/addForm";
//        }

        PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
        prescriptionDrug.setDurationStart(form.getDrugStart());
        prescriptionDrug.setDurationEnd(form.getDrugEnd());
        prescriptionDrug.setDosesCount(form.getDosesCount());
        Drug findDrug = drugService.findByName(form.getDrugName());
        prescriptionDrug.setDrug(findDrug);
        prescriptionDrug.setMedicalRecord(medicalRecordService.addRecordToPrescription(form.getRecordId()));

        prescriptionService.add(prescriptionDrug);
        redirectAttributes.addFlashAttribute("result", "등록이 완료되었습니다");

        return "redirect:/prescriptions/list";
    }

    /**
     *
     * @return 등록된 약목록
     */
    @GetMapping("/search")
    private String searchDrug(Model model){
        List<Drug> drugs = drugService.findAll();
        model.addAttribute("drugs",drugs);

        return "prescriptions/searchDrug";
    }


}
