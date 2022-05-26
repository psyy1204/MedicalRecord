package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.LoginForm;
import Medical.MedicalRecord.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/members/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm form) {
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                         HttpServletRequest request,
                         @RequestParam(defaultValue = "/") String redirectURL) {

        if (bindingResult.hasErrors()) {
            return "members/login";
        }

        Member loginMember = loginService.login(form.getEmail(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        session.setMaxInactiveInterval(1800);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
