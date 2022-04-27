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

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public List<Integer> home() throws ParseException {

        List<Integer> countRecord = medicalRecordService.countRecord();
        for (int i = 0; i<12; i++) {
            System.out.println("출력 = " + countRecord.get(i));
        }
        return countRecord;
    }

    @GetMapping("/")
    public String index(Model model) {
        int price = medicalRecordService.totalPrice();
        System.out.println("price = " + price);
        model.addAttribute("totalPrice", price);
        return "index";
    }

}

