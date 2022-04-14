package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.DrugComponent;
import Medical.MedicalRecord.repository.DrugComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugComponentController {

    private final DrugComponentRepository drugComponentRepository;

    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String drugs(Model model) {
        List<DrugComponent> drugComponents = drugComponentRepository.findAll();
        model.addAttribute("drugs", drugComponents);
        return "drugs/drugList";
    }

    /**
     * 아이디로 가져오기
     */
    @GetMapping("/drug/{drugId}")
    public String drug(@PathVariable long drugId, Model model) {
        DrugComponent drugComponent = drugComponentRepository.findById(drugId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 데이터입니다"));
        model.addAttribute("drug", drugComponent);
        return "drugs/drug";
    }

    /**
     * 약 성분명 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("drug", new DrugComponent());
        return "drugs/addForm";
    }

    /**
     * 등록
     */
    @PostMapping("/new")
    public String addDrug(DrugComponent drugComponent, RedirectAttributes redirectAttributes) {
        DrugComponent saveDrug = drugComponentRepository.save(drugComponent);
        redirectAttributes.addAttribute("drugId", saveDrug.getComponentId());
        return "redirect:/drugs/drug/{drugId}";
    }

    /**
     * 수정폼
     */
    @GetMapping("/drug/{drugId}/edit")
    public String editForm(@PathVariable Long drugId, Model model) {
        DrugComponent drugComponent = drugComponentRepository.findById(drugId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 데이터입니다"));
        model.addAttribute("drug",drugComponent);
        return "drugs/editForm";
    }

    /**
     * 수정
     */
    @PostMapping("/drug/{drugId}/edit")
    public String edit(@PathVariable Long drugId, @ModelAttribute DrugComponent newdrug){
        drugComponentRepository.findById(drugId)
                .map(drug -> {
                    drug.setComponentName(newdrug.getComponentName());
                    return drugComponentRepository.save(drug);
                })
                .orElseThrow(() -> new IllegalArgumentException("조회하는 약 성분명이 없습니다"));
                return "redirect:/drugs/drug/{drugId}";
    }

    /**
     * 삭제
     */
    @GetMapping("/drug/{drugId}/delete")
    public String deleteDrug(@PathVariable("drugId") Long id){
        drugComponentRepository.deleteById(id);
        return "redirect:/drugs/list";
    }
}

