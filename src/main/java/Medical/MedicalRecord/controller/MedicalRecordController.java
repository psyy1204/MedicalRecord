package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.MedicalDepartmentCode;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.form.MedicalRecordForm;
import Medical.MedicalRecord.service.HospitalService;
import Medical.MedicalRecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String addHospital(@Valid MedicalRecordForm form, BindingResult result) {
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

        medicalRecordService.add(medicalRecord);

        return "redirect:/records/list";
    }

    /**
     * No로 가져오기
     */
    @GetMapping("/{recordId}")
    public String record(@PathVariable long recordId, Model model){
        MedicalRecord medicalRecord = medicalRecordService.findById(recordId);
        model.addAttribute("record", medicalRecord);
        return "records/record";
    }


    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String records(Model model){
        List<MedicalRecord> records = medicalRecordService.findAll();
        model.addAttribute("records",records);
        return "records/recordList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{recordId}/edit")
    public String editForm(@PathVariable("recordId") Long recordId, Model model) {
        MedicalRecord medicalRecord = medicalRecordService.findById(recordId);

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
        form.setUpdateDate(LocalDateTime.now());

        model.addAttribute("form", form);
        return "records/editForm";
    }

    /**
     * 수정
     */

    @PostMapping("/{recordId}/edit")
    public String edit(@PathVariable Long recordId,
                       @ModelAttribute("form") @Valid MedicalRecordForm form,
                       BindingResult result) {

        if(result.hasErrors()) {
            return "members/editForm";
        }
        medicalRecordService.editRecord(recordId, form.getDoctorName(),
                form.getHospitalName(), form.getMedicalDepartmentCode(),
                form.getEtc(),form.getPrice(),form.getVisitedDate(),form.getNextVisitDate());

        return "redirect:/records/list";
    }

    /**
     * 삭제
     */
    @GetMapping("/{recordId}/delete")
    public String deleteRecord(@PathVariable("recordId") Long id){
        medicalRecordService.deleteRecord(id);
        return "redirect:/records/list";
    }

}
