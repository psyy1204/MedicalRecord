package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Symptom;
import Medical.MedicalRecord.form.SymptomForm;
import Medical.MedicalRecord.service.SymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/symptoms")
@RequiredArgsConstructor
public class SymptomController {

    private final SymptomService symptomService;

    /**
     * 기록 등록폼
     */
    @GetMapping("/new")
    public String addForm(@RequestParam(value = "recordId", required = false) Long recordId ,Model model) {
        SymptomForm symptomForm = new SymptomForm();
        symptomForm.setMedicalRecordId(recordId);
        model.addAttribute("symptomForm", symptomForm);
        return "symptoms/addForm";
    }

    /**
     * 기록 등록
     */
    @PostMapping("/new")
    public String addSymptom(@Valid SymptomForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "symptoms/addForm";
        }

        Symptom symptom = new Symptom();
        symptom.setSimpleSymptom(form.getSimpleSymptom());
        symptom.setDetailSymptom(form.getDetailSymptom());
        symptom.setStartDate(form.getStartDate());
        symptom.setPulse(form.getPulse());
        symptom.setOxygenSaturation(form.getOxygenSaturation());
        symptom.setBodyTemperature(form.getBodyTemperature());
        symptom.setSystolic(form.getSystolic());
        symptom.setDiastolic(form.getDiastolic());
        symptom.setCreatedDate(LocalDateTime.now());
        symptom.setUpdatedDate(LocalDateTime.now());

        symptomService.add(symptom);
        if(form.getMedicalRecordId() != null) {
            symptomService.addSymptomToRecord(form.getMedicalRecordId(), symptom);
        }

        return "redirect:/symptoms/list";
    }

    /**
     * No로 가져오기
     */
    @GetMapping("/{symptomId}")
    public String symptom(@PathVariable long symptomId, Model model){
        Symptom symptom = symptomService.findById(symptomId);
        model.addAttribute("symptom", symptom);
        return "symptoms/symptom";
    }


    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String symptoms(Model model){
        List<Symptom> symptoms = symptomService.findAll();
        model.addAttribute("symptoms",symptoms);
        return "symptoms/symptomList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{symptomId}/edit")
    public String editForm(@PathVariable("symptomId") Long symptomId, Model model) {
        Symptom symptom = symptomService.findById(symptomId);

        SymptomForm form = new SymptomForm();

        form.setId(symptom.getSymptomId());
        form.setSimpleSymptom(symptom.getSimpleSymptom());
        form.setDetailSymptom(symptom.getDetailSymptom());
        form.setStartDate(symptom.getStartDate());
        form.setSystolic(symptom.getSystolic());
        form.setDiastolic(symptom.getDiastolic());
        form.setBodyTemperature(symptom.getBodyTemperature());
        form.setOxygenSaturation(symptom.getOxygenSaturation());
        form.setPulse(symptom.getPulse());
        form.setUpdateDate(LocalDateTime.now());

        model.addAttribute("form", form);
        return "symptoms/editForm";
    }

    /**
     * 수정
     */

    @PostMapping("/{symptomId}/edit")
    public String edit(@PathVariable Long symptomId,
                       @ModelAttribute("form") @Valid SymptomForm form,
                       BindingResult result) {

        if(result.hasErrors()) {
            return "symptoms/editForm";
        }
        symptomService.editSymptom(symptomId, form.getSimpleSymptom(),
                form.getDetailSymptom(),form.getStartDate(), form.getBodyTemperature(),
                form.getPulse(),form.getSystolic(),form.getDiastolic(),form.getOxygenSaturation());

        return "redirect:/symptoms/list";
    }

    /**
     * 삭제
     */
    @GetMapping("/{symptomId}/delete")
    public String deleteSymptom(@PathVariable("symptomId") Long id){
        symptomService.deleteSymptom(id);
        return "redirect:/symptoms/list";
    }

}
