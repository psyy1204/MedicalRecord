package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import session.SessionConst;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MedicalRecordService medicalRecordService;


    @GetMapping("/")
    public String index2(Model model,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                         Member loginMember){

        int price = medicalRecordService.totalPrice();
        model.addAttribute("totalPrice", price);

        if (loginMember == null) {
            return "index";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

}

