package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;
import Medical.MedicalRecord.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ModelAttribute("gender")
    public Gender[] gender() {
        return Gender.values();
    }

    /**
     * 가입폼
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/addForm";
    }

    /**
     * 회원 등록
     */
    @PostMapping("/new")
    public String addMember(@Valid MemberForm form, BindingResult result) {

        if(result.hasErrors()) {
            return "members/addForm";
        }

        Member member = new Member();
        member.setAge(form.getAge());
        member.setEmail(form.getEmail());
        member.setUserName(form.getUsername());
        member.setHeight(form.getHeight());
        member.setWeight(form.getWeight());
        member.setGender(form.getGender());
        member.setCreatedDate(LocalDateTime.now());
        member.setUpdatedDate(LocalDateTime.now());

        memberService.join(member);
        return "redirect:/members/list";
    }

    /**
     * 한개 조회
     */
    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "members/member";
    }

    /**
     * 전체리스트 보기
     */
    @GetMapping("/list")
    public String members(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    /**
     * 회원수정폼
     */
    @GetMapping("/{memberId}/edit")
    public String editMemberForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findById(memberId);

        MemberForm form = new MemberForm();
        form.setId(member.getMemberId());
        form.setAge(member.getAge());
        form.setEmail(member.getEmail());
        form.setUsername(member.getUserName());
        form.setHeight(member.getHeight());
        form.setWeight(member.getWeight());
        form.setGender(member.getGender());

        model.addAttribute("form", form);
        return "members/editForm";
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable Long memberId,
                             @ModelAttribute("form") @Valid MemberForm form,
                            BindingResult result) {

        if(result.hasErrors()) {
            return "members/editForm";
        }

        memberService.editMember(memberId, form.getUsername(),
                form.getAge(),form.getGender(),form.getHeight(),
                form.getWeight());

        return "redirect:/members/list";
    }

    /**
     * 삭제
     */
    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable("memberId") Long id){
        memberService.deleteMember(id);
        return "redirect:/members/list";
    }


    /**
     *
     * @param memberId
     * @return 아이디로 조회하기
     */
//    @GetMapping("/member/{memberId}")
//    public String member(@PathVariable long memberId, Model model) {
//        Member member = memberService.findById(memberId);
//        model.addAttribute("member", member);
//        return "members/member";
//    }
}
