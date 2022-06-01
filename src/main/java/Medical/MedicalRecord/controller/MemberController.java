package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;
import Medical.MedicalRecord.paging.Pagination;
import Medical.MedicalRecord.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addMember(@Valid MemberForm form,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "members/addForm";
        }

        if (!form.getPassword().equals(form.getPasswordCheck())) {
            result.addError(new FieldError("member", "password","비밀번호와 확인값이 다릅니다."));
            return "members/addForm";
        }

        Member member = new Member();
        member.setAge(form.getAge());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setUserName(form.getUsername());
        member.setNickName(form.getNickName());
        member.setHeight(form.getHeight());
        member.setWeight(form.getWeight());
        member.setGender(form.getGender());
        member.setCreatedDate(LocalDateTime.now());
        member.setUpdatedDate(LocalDateTime.now());

        memberService.join(member);
        redirectAttributes.addFlashAttribute("result", "가입이 완료되었습니다");
        return "redirect:/members/login";
    }

    /**
     * 한개 조회
     */
    @GetMapping("/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberService.findById(memberId);
        if(member == null) {
            return "error-page/404";
        } else {
            model.addAttribute("member", member);
            return "members/member";
        }
    }

    /**
     * 전체리스트 보기
     */
    @GetMapping("/list")
    public String members(Model model, @RequestParam(defaultValue = "1") int page) {
        // 총 게시물 수
        int totalListCount = memberService.findAllCount();
        // 생성인자로  총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalListCount, page);

        // DB select start index
        int startIndex = pagination.getStartDbIndex();
        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getDataPerPageSize();

        List<Member> members = memberService.findListPaging(startIndex, pageSize);
        model.addAttribute("members", members);
        model.addAttribute("pagination", pagination);

        return "members/memberList";
    }

    /**
     * 회원수정폼
     */
    @GetMapping("/{memberId}/edit")
    public String editMemberForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findById(memberId);
        if (member == null) {
            return "error-page/404";
        } else {

            MemberForm form = new MemberForm();
            form.setId(member.getMemberId());
            form.setAge(member.getAge());
            form.setEmail(member.getEmail());
            form.setUsername(member.getUserName());
            form.setNickName(member.getNickName());
            form.setHeight(member.getHeight());
            form.setWeight(member.getWeight());
            form.setGender(member.getGender());

            model.addAttribute("memberForm", form);
            return "members/editForm";
        }
    }

    @PostMapping("/{memberId}/edit")
    public String editMember(@PathVariable Long memberId,
                             @ModelAttribute("form") @Valid MemberForm form,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (memberService.findById(memberId)==null) {
            return "error-page/404";
        } else if (result.hasErrors()) {
            return "members/editForm";
        } else {

            memberService.editMember(memberId, form.getUsername(), form.getNickName(),
                    form.getAge(), form.getGender(), form.getHeight(),
                    form.getWeight());
            redirectAttributes.addFlashAttribute("result", "수정이 완료되었습니다");

            return "redirect:/members/list";
        }
    }

    /**
     * 삭제
     */
    @GetMapping("/{memberId}/delete")
    public String deleteMember(@PathVariable("memberId") Long id,
                               RedirectAttributes redirectAttributes) {
        if(memberService.findById(id) == null) {
            return "error-page/404";
        } else {
            memberService.deleteMember(id);
            redirectAttributes.addFlashAttribute("result", "삭제가 완료되었습니다");
            return "redirect:/members/list";
        }
    }
}
