package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    /**
     * 전체 리스트
     */
    @GetMapping("/list")
    public String hospitals(Model model) {
        List<Hospital> hospitals = hospitalRepository.findAll();
        model.addAttribute("hospitals", hospitals);
        return "hospitals/hospitalList";
    }

    /**
     * 아이디로 가져오기
     */
    @GetMapping("/hospital/{hospitalId}")
    public String hospital(@PathVariable long hospitalId, Model model){
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 데이터입니다"));
        model.addAttribute("hospital", hospital);
        return "hospitals/hospital";
    }

    /**
     * 병원 등록폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospitals/addForm";
    }

    /**
     * 등록
     */
    @PostMapping("/new")
    public String addHospital(Hospital hospital, RedirectAttributes redirectAttributes) {
        Hospital saveHospital = hospitalRepository.save(hospital);
        redirectAttributes.addAttribute("hospitalId", saveHospital.getHospitalId());
        return "redirect:/hospitals/hospital/{hospitalId}";
    }

    /**
     * 수정폼
     */
    @GetMapping("/hospital/{hospitalId}/edit")
    public String editForm(@PathVariable Long hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() ->  new IllegalArgumentException("존재하지 않는 병원입니다"));
        model.addAttribute("hospital", hospital);
        return "hospitals/editForm";
    }

    /**
     * 수정
     */

    @PostMapping("/hospital/{hospitalId}/edit")
    public String edit(@PathVariable Long hospitalId, @ModelAttribute Hospital newhospital) {
        hospitalRepository.findById(hospitalId)
                .map(hospital -> {
                    hospital.setHospitalName(newhospital.getHospitalName());
                    hospital.setHospitalAddress(newhospital.getHospitalAddress());
                    hospital.setHospitalContact(newhospital.getHospitalContact());
                    return hospitalRepository.save(hospital);
                })
                .orElseThrow(() -> new IllegalArgumentException("조회하는 병원이 없습니다"));
        return "redirect:/hospitals/hospital/{hospitalId}";
    }

    @GetMapping("/hospital/{hospitalId}/delete")
    public String deleteForm(@PathVariable Long hospitalId, Model model) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() ->  new IllegalArgumentException("존재하지 않는 병원입니다"));
        model.addAttribute("hospital", hospital);
        return "hospitals/delete";
    }

    @DeleteMapping("/hospital/{hospitalId}/delete")
    public String delete(@PathVariable("hospitalId") Long id) {
        hospitalRepository.deleteById(id);
        return "redirect:/hospitals/list";
    }
}
