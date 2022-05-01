package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping("/")
    public String index(Model model) {
        int price = medicalRecordService.totalPrice();
        model.addAttribute("totalPrice", price);
        return "index";
    }

}

