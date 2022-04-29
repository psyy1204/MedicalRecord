package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.service.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

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

