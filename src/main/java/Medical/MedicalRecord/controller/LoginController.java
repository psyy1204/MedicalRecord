package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.form.MemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class LoginController {

    @ModelAttribute("gender")
    public Gender[] gender() {
            return Gender.values();
    }

    @GetMapping("/new")
    public String loginForm(@ModelAttribute("memberForm") MemberForm form) {
        return "members/addForms";
    }

}
