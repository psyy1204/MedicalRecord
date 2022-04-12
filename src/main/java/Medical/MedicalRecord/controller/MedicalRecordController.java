package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.domain.MedicalDepartmentCode;
import Medical.MedicalRecord.domain.MedicalRecord;
import Medical.MedicalRecord.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @ModelAttribute("medicalDepartmentCodes")
    public List<MedicalDepartmentCode> deliveryCodes() {
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
     * 전체 리스트
     */
    @GetMapping("/list")
    public String records(Model model){
        List<MedicalRecord> records = medicalRecordRepository.findAll();
        model.addAttribute("records",records);
        return "records/recordList";
    }

    /**
     * No로 가져오기
     */
    @GetMapping("/record/{recordId}")
    public String record(@PathVariable long recordId, Model model){
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 데이터입니다"));
        model.addAttribute("record", medicalRecord);
        return "records/record";
    }

    /**
     * 기록 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("record", new MedicalRecord());
        return "records/addForm";
    }

    /**
     * 진료기록 등록
     */
    @PostMapping("/new")
    public String addHospital(MedicalRecord medicalRecord, RedirectAttributes redirectAttributes) {
        MedicalRecord saveRecord = medicalRecordRepository.save(medicalRecord);
        redirectAttributes.addAttribute("recordId", saveRecord.getRecordId());
        return "redirect:/records/record/{recordId}";
    }

    /**
     * 수정폼
     */
    @GetMapping("/record/{recordId}/edit")
    public String editForm(@PathVariable Long recordId, Model model) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() ->  new IllegalArgumentException("존재하지 않는 기록입니다"));
        model.addAttribute("record", medicalRecord);
        return "records/editForm";
    }

    /**
     * 수정
     */

    @PostMapping("/record/{recordId}/edit")
    public String edit(@PathVariable Long recordId, @ModelAttribute MedicalRecord newRecord) {
        medicalRecordRepository.findById(recordId)
                .map(record -> {
                    record.setDoctorName(newRecord.getDoctorName());
                    record.setDiagnosis(newRecord.getDiagnosis());
                    record.setMedicalDepartmentCode(newRecord.getMedicalDepartmentCode());
                    record.setEtc(newRecord.getEtc());
                    record.setPrice(newRecord.getPrice());
                    record.setVisitedDate(newRecord.getVisitedDate());
                    record.setNextVisitDate(newRecord.getNextVisitDate());
                    record.setUpdatedDate(newRecord.getUpdatedDate());
                    return medicalRecordRepository.save(record);
                })
                .orElseThrow(() -> new IllegalArgumentException("조회하는 병원이 없습니다"));
        return "redirect:/records/record/{recordId}";
    }

    /**
     * 삭제
     */
    @GetMapping("/record/{recordId}/delete")
    public String deleteHospital(@PathVariable("recordId") Long id){
        medicalRecordRepository.deleteById(id);
        return "redirect:/records/list";
    }

}
