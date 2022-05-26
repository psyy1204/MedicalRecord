package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.MedicalDepartmentCode;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.form.MedicalRecordForm;
import Medical.MedicalRecord.paging.Pagination;
import Medical.MedicalRecord.service.HospitalService;
import Medical.MedicalRecord.service.MedicalRecordService;
import Medical.MedicalRecord.service.SymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final HospitalService hospitalService;
    private final SymptomService symptomService;

    @ModelAttribute("medicalDepartmentCodes")
    public List<MedicalDepartmentCode> medicalDepartmentCodes() {
        List<MedicalDepartmentCode> medicalDepartmentCodes = new ArrayList<>();
        medicalDepartmentCodes.add(new MedicalDepartmentCode("IM", "내과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("GS", "일반외과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("RM", "재활의학과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("OS", "정형외과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("ER", "응급의학과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("NE", "신경과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("NS", "신경외과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("UR", "비뇨기과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("FM", "가정의학과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("DT", "치과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("OB", "산부인과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("NP", "정신과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("OT", "안과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("ENT", "이비인후과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("PD", "소아과"));
        medicalDepartmentCodes.add(new MedicalDepartmentCode("ETC", "기타"));
        return medicalDepartmentCodes;
    }

    /**
     * 기록 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("medicalRecordForm", new MedicalRecordForm());
        return "records/addForm";
    }

    /**
     * 진료기록 등록
     */
    @PostMapping("/new")
    public String addHospital(@Valid MedicalRecordForm form, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "records/addForm";
        }

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
        redirectAttributes.addFlashAttribute("result", "등록이 완료되었습니다");
        return "redirect:/records/list";
    }

    /**
     * No로 가져오기
     */
    @GetMapping("/{recordId}")
    public String record(@PathVariable long recordId, Model model){
        MedicalRecord medicalRecord = medicalRecordService.findById(recordId);
        if(medicalRecord == null) {
            return "error-page/404";
        } else {
            model.addAttribute("record", medicalRecord);
            return "records/record";
        }
    }


    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String records(Model model, @RequestParam(defaultValue = "1") int page){

        int totalListCount = medicalRecordService.findAllCount();

        Pagination pagination = new Pagination(totalListCount, page);
        int startIndex = pagination.getStartDbIndex();
        int pageSize = pagination.getDataPerPageSize();

        List<MedicalRecord> records = medicalRecordService.findListPaging(startIndex, pageSize);
        model.addAttribute("pagination", pagination);
        model.addAttribute("records",records);
        return "records/recordList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{recordId}/edit")
    public String editForm(@PathVariable("recordId") Long recordId, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.findById(recordId);
        if (medicalRecord == null) {
            return "error-page/404";
        } else {
            MedicalRecordForm form = new MedicalRecordForm();
            form.setId(medicalRecord.getRecordId());
            form.setDoctorName(medicalRecord.getDoctorName());
            form.setHospitalName(medicalRecord.getHospital().getHospitalName());
            form.setDiagnosis(medicalRecord.getDiagnosis());
            form.setMedicalDepartmentCode(medicalRecord.getMedicalDepartmentCode());
            form.setEtc(medicalRecord.getEtc());
            form.setPrice(medicalRecord.getPrice());
            form.setVisitedDate(medicalRecord.getVisitedDate());
            form.setNextVisitDate(medicalRecord.getNextVisitDate());
            form.setMemberId(form.getMemberId());
            form.setUpdateDate(LocalDateTime.now());

            model.addAttribute("form", form);
            return "records/editForm";
        }
    }

    /**
     * 수정
     */

    @PostMapping("/{recordId}/edit")
    public String edit(@PathVariable Long recordId,
                       @ModelAttribute("form") @Valid MedicalRecordForm form,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {

        if (medicalRecordService.findById(recordId)== null) {
            return "error-page/404";
        } else if(result.hasErrors()) {
            return "members/editForm";
        } else {
            medicalRecordService.editRecord(recordId, form.getDoctorName(),
                    form.getHospitalName(), form.getMedicalDepartmentCode(),
                    form.getEtc(), form.getPrice(), form.getMemberId(), form.getVisitedDate(), form.getNextVisitDate());

            redirectAttributes.addFlashAttribute("result", "수정이 완료되었습니다");
            return "redirect:/records/list";
        }
    }

    /**
     * 삭제
     */
    @GetMapping("/{recordId}/delete")
    public String deleteRecord(@PathVariable("recordId") Long id,
                               RedirectAttributes redirectAttributes){
        MedicalRecord findRecord = medicalRecordService.findById(id);
        if (findRecord == null) {
            return "error-page/404";
        } else if (findRecord.isHasDrug()){
            redirectAttributes.addFlashAttribute
                    ("result","처방약이 등록되어있어 삭제가 불가능합니다.(삭제 후 다시 시도해주세요)");
            return "redirect:/records/list";
        } else {
            medicalRecordService.deleteRecord(id);
            if(findRecord.isHasSymptom()) {
                Long symptomId = findRecord.getSymptom().getSymptomId();
                symptomService.deleteSymptom(symptomId);
            }
            redirectAttributes.addFlashAttribute("result", "삭제가 완료되었습니다");
            return "redirect:/records/list";
        }
    }
}
