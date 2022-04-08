package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Hospital;
import Medical.MedicalRecord.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        redirectAttributes.addAttribute("hospital", saveHospital.getHospitalId());
        return "hospital/{hospitalId}";
    }


}
