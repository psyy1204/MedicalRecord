package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.form.HospitalForm;
import Medical.MedicalRecord.paging.Pagination;
import Medical.MedicalRecord.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    /**
     * 병원 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "hospitals/addForm";
    }

    /**
     * 등록
     */
    @PostMapping("/new")
    public String addHospital(@Valid HospitalForm form, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "hospitals/addForm";
        }
        Hospital hospital = new Hospital();
        hospital.setHospitalName(form.getHospitalName());
        hospital.setHospitalContact(form.getHospitalContact());
        hospital.setHospitalAddress(form.getHospitalAddress());

        hospitalService.add(hospital);
            redirectAttributes.addFlashAttribute("result", "등록이 완료되었습니다");
        return "redirect:/hospitals/list";
    }

    /**
     * 아이디로 가져오기
     */
    @GetMapping("/{hospitalId}")
    public String hospital(@PathVariable long hospitalId, Model model){
        Hospital hospital = hospitalService.findById(hospitalId);
        if(hospital == null) {
            return "error-page/404";
        } else {
            model.addAttribute("hospital", hospital);
            return "hospitals/hospital";
        }
    }

    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String hospitals(Model model, @RequestParam(defaultValue = "1") int page) {

        int totalListCount = hospitalService.findAllCount();

        Pagination pagination = new Pagination(totalListCount, page);
        int startIndex = pagination.getStartDbIndex();
        int pageSize = pagination.getDataPerPageSize();

        List<Hospital> hospitals = hospitalService.findListPaging(startIndex, pageSize);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("pagination", pagination);

        return "hospitals/hospitalList";
    }

    /**
     * 수정폼
     */
    @GetMapping("/{hospitalId}/edit")
    public String editForm(@PathVariable("hospitalId") Long hospitalId, Model model) {
        Hospital hospital = hospitalService.findById(hospitalId);
        if(hospital == null) {
            return "error-page/404";
        } else {
            HospitalForm form = new HospitalForm();
            form.setHospitalId(hospital.getHospitalId());
            form.setHospitalName(hospital.getHospitalName());
            form.setHospitalAddress(hospital.getHospitalAddress());
            form.setHospitalContact(hospital.getHospitalContact());

            model.addAttribute("form", form);
            return "hospitals/editForm";
        }
    }

    /**
     * 수정
     */

    @PostMapping("/{hospitalId}/edit")
    public String edit(@PathVariable Long hospitalId,
                       @ModelAttribute("form") @Valid HospitalForm form,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (hospitalService.findById(hospitalId) == null) {
            return "error-page/404";
        } else if(result.hasErrors()) {
            return "hospitals/editForm";
        } else {

            hospitalService.edit(hospitalId,
                    form.getHospitalName(),
                    form.getHospitalAddress(),
                    form.getHospitalContact());
            redirectAttributes.addFlashAttribute("result", "수정이 완료되었습니다");

            return "redirect:/hospitals/list";
        }
    }

    /**
     * 삭제
     */
    @GetMapping("/{hospitalId}/delete")
    public String deleteHospital(@PathVariable("hospitalId") Long id,
                                 RedirectAttributes redirectAttributes){
        if (hospitalService.findById(id) == null) {
            return "error-page/404";
        } else {
            hospitalService.delete(id);
            redirectAttributes.addFlashAttribute("result", "삭제가 완료되었습니다");
            return "redirect:/hospitals/list";
        }
    }
}
