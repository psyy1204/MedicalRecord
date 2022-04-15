package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.DrugComponent;

import Medical.MedicalRecord.form.DrugForm;
import Medical.MedicalRecord.service.DrugComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/drugs")
@RequiredArgsConstructor
public class DrugComponentController {

    private final DrugComponentService drugComponentService;


    /**
     * 약 성분명 등록폼
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
    public String addHospital(@Valid DrugComponent form, BindingResult result) {
        if(result.hasErrors()) {
            return "drugs/addForm";
        }
        DrugComponent drugComponent = new DrugComponent();
        drugComponent.setComponentName(form.getComponentName());
        drugComponentService.add(drugComponent);

        return "redirect:/drugs/list";
    }

    /**
     * 아이디로 가져오기
     */
    @GetMapping("/{drugId}")
    public String drug(@PathVariable long drugId, Model model) {
        DrugComponent drugComponent = drugComponentService.findById(drugId);
        model.addAttribute("drug", drugComponent);
        return "drugs/drug";
    }

    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String drugs(Model model) {
        List<DrugComponent> drugComponents = drugComponentService.findAll();
        model.addAttribute("drugs", drugComponents);
        return "drugs/drugList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{drugId}/edit")
    public String editForm(@PathVariable("drugId") Long drugId, Model model) {
        DrugComponent drugComponent = drugComponentService.findById(drugId);

        DrugForm form = new DrugForm();
        form.setComponentName(drugComponent.getComponentName());

        model.addAttribute("form",form);
        return "drugs/editForm";
    }

    /**
     * 수정
     */
    @PostMapping("/{drugId}/edit")
    public String edit(@PathVariable Long drugId,
                       @ModelAttribute("form") @Valid DrugForm form,
                       BindingResult result){
        if(result.hasErrors()) {
            return "drugs/editForm";
        }
        drugComponentService.edit(drugId, form.getComponentName());

        return "redirect:/drugs/list";
    }

    /**
     * 삭제
     */
    @GetMapping("/{drugId}/delete")
    public String deleteDrug(@PathVariable("drugId") Long id){
        drugComponentService.delete(id);
        return "redirect:/drugs/list";
    }
}

