package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Drug;

import Medical.MedicalRecord.form.DrugForm;
import Medical.MedicalRecord.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;


    /**
     * 약 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("drugForm", new DrugForm());
        return "drugs/addForm";
    }

    /**
     * 등록
     */
    @PostMapping("/new")
    public String addDrug(@Valid DrugForm form, BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "drugs/addForm";
        }
        Drug drug = new Drug();
        drug.setDrugName(form.getDrugName());
        drug.setDrugComponent(form.getDrugComponent());
        drugService.add(drug);

        redirectAttributes.addFlashAttribute("result", "등록이 완료되었습니다");

        return "redirect:/drugs/list";
    }

    /**
     * 아이디로 가져오기
     */
    @GetMapping("/{drugId}")
    public String drug(@PathVariable long drugId, Model model) {
        Drug drug = drugService.findById(drugId);
        model.addAttribute("drug", drug);
        return "drugs/drug";
    }

    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String drugs(Model model) {
        List<Drug> drugs = drugService.findAll();
        model.addAttribute("drugs", drugs);
        return "drugs/drugList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{drugId}/edit")
    public String editForm(@PathVariable("drugId") Long drugId, Model model) {
        Drug drug = drugService.findById(drugId);

        DrugForm form = new DrugForm();
        form.setDrugName(drug.getDrugName());
        form.setDrugComponent(drug.getDrugComponent());
        model.addAttribute("form",form);
        return "drugs/editForm";
    }

    /**
     * 수정
     */
    @PostMapping("/{drugId}/edit")
    public String edit(@PathVariable Long drugId,
                       @ModelAttribute("form") @Valid DrugForm form,
                       BindingResult result,
                       RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            return "drugs/editForm";
        }
        drugService.edit(drugId, form.getDrugName(), form.getDrugComponent());

        redirectAttributes.addFlashAttribute("result", "수정이 완료되었습니다");
        return "redirect:/drugs/list";
    }

    /**
     * 삭제
     */
    @GetMapping("/{drugId}/delete")
    public String deleteDrug(@PathVariable("drugId") Long id,
                             RedirectAttributes redirectAttributes){
        drugService.delete(id);

        redirectAttributes.addFlashAttribute("result", "삭제가 완료되었습니다");
        return "redirect:/drugs/list";
    }
}

