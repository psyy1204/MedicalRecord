package Medical.MedicalRecord.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController {

    @RequestMapping("/errors")
    public String basicError() {
        return "error-page/404";
    }
}
