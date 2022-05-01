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
import org.springframework.web.bind.annotation.*;
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
    public String addForm(@RequestParam(value = "recordId", required = false) Long recordId, Model model) {
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
        if (result.hasErrors()) {
            return "prescriptions/addForm";
        }

        PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
        prescriptionDrug.setDurationStart(form.getDrugStart());
        prescriptionDrug.setDurationEnd(form.getDrugEnd());
        prescriptionDrug.setDosesCount(form.getDosesCount());
        Drug findDrug = drugService.findByName(form.getDrugName());
        prescriptionDrug.setDrug(findDrug);
        prescriptionDrug.setMedicalRecord(medicalRecordService.addRecordToPrescription(form.getRecordId()));

        prescriptionService.add(prescriptionDrug);
        redirectAttributes.addFlashAttribute("result", "등록이 완료되었습니다");

        return "redirect:/prescriptions/list/" + prescriptionDrug.getMedicalRecord().getRecordId();
    }

    /**
     * @return 등록된 약목록
     */
    @GetMapping("/search")
    public String searchDrug(Model model) {
        List<Drug> drugs = drugService.findAll();
        model.addAttribute("drugs", drugs);

        return "prescriptions/searchDrug";
    }

    /**
     * @param id
     * @return 진료기록에 해당하는 처방약 리스트
     */
    @GetMapping("/list/{recordId}")
    public String prescriptionList(@PathVariable("recordId") Long id,
                                   Model model) {
        List<PrescriptionDrug> recordPrescription = prescriptionService.findRecordPrescription(id);
        if (medicalRecordService.findById(id) == null) {
            return "error-page/404";
        } else {
            model.addAttribute("recordId", id);
            model.addAttribute("prescriptions", recordPrescription);
            return "prescriptions/prescriptionList";
        }
    }

    /**
     * @param prescriptionId
     * @return id에 해당하는 기록
     */
    @GetMapping("/{prescriptionId}")
    public String prescription(@PathVariable long prescriptionId,
                               Model model) {
        PrescriptionDrug prescriptionDrug = prescriptionService.findById(prescriptionId);
        if (prescriptionDrug == null) {
            return "error-page/404";
        } else {
            model.addAttribute("prescription", prescriptionDrug);
            return "prescriptions/prescription";
        }
    }

    /**
     * @param prescriptionId
     * @return 수정폼
     */
    @GetMapping("/{prescriptionId}/edit")
    public String editForm(@PathVariable("prescriptionId") Long prescriptionId,
                           Model model) {
        PrescriptionDrug prescriptionDrug = prescriptionService.findById(prescriptionId);
        if (prescriptionDrug == null) {
            return "error-page/404";
        } else {
            PrescriptionForm form = new PrescriptionForm();
            form.setDrugName(prescriptionDrug.getDrug().getDrugName());
            form.setDosesCount(prescriptionDrug.getDosesCount());
            form.setDrugStart(prescriptionDrug.getDurationStart());
            form.setDrugEnd(prescriptionDrug.getDurationEnd());
            form.setRecordId(prescriptionDrug.getMedicalRecord().getRecordId());

            model.addAttribute("form", form);
            return "prescriptions/editForm";
        }
    }

    @PostMapping("/{prescriptionId}/edit")
    public String edit(@PathVariable Long prescriptionId,
                       @ModelAttribute("form") @Valid PrescriptionForm form,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (prescriptionService.findById(prescriptionId) == null) {
            return "error-page/404";
        } else if (result.hasErrors()) {
            return "prescriptions/editForm";
        } else {
            prescriptionService.editPrescription(prescriptionId, form.getDrugStart(),
                    form.getDrugEnd(), form.getDosesCount());
            redirectAttributes.addFlashAttribute("result", "수정이 완료되었습니다");
            Long recordId = prescriptionService.findById(prescriptionId).getMedicalRecord().getRecordId();
            return "redirect:/prescriptions/list/" + recordId;
        }
    }


    /**
     * 삭제
     */
    @GetMapping("/{prescriptionId}/delete")
    public String deletePrescription(@PathVariable("prescriptionId") Long id,
                                     RedirectAttributes redirectAttributes) {
        if (prescriptionService.findById(id) == null) {
            return "error-page/404";
        } else {
            Long recordId = prescriptionService.findById(id).getMedicalRecord().getRecordId();
            prescriptionService.deletePrescription(id, recordId);
            redirectAttributes.addFlashAttribute("result", "삭제가 완료되었습니다");
            return "redirect:/records/list";
        }
    }
}
