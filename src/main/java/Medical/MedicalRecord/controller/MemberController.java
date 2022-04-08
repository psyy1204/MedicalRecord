package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

import static Medical.MedicalRecord.domain.Gender.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @ModelAttribute("gender")
    public Gender[] gender() {
        return Gender.values();
    }

    /**
     * 전체리스트 보기
     */
    @GetMapping("/list")
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/member/{memberId}")
    public String member(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 데이터입니다."));
        model.addAttribute("member", member);
        return "members/member";
    }

    /**
     * 가입 폼 띄우기
     */
    @GetMapping("/new")
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/addForm";
    }

    @PostMapping("/new")
    public String addMember(Member member, RedirectAttributes redirectAttributes) {
        Member saveMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", saveMember.getMemberId());
        //redirectAttributes.addAttribute("status", true);
        return "redirect:/members/member/{memberId}";
    }

    /**
     * 수정폼
     */
    @GetMapping("/member/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다."));
        model.addAttribute("member", member);
        return "members/editForm";
    }
    /**
     * 수정
     */
    @PostMapping("/member/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute Member newMember) {
        memberRepository.findById(memberId)
                .map(member -> {
                    member.setUserName(newMember.getUserName());
                    member.setAge(newMember.getAge());
                    member.setEmail(newMember.getEmail());
                    member.setGender(newMember.getGender());
                    member.setHeight(newMember.getHeight());
                    member.setWeight(newMember.getWeight());
                    member.setUpdatedDate(newMember.getUpdatedDate());
                    return memberRepository.save(member);
                })
                .orElseThrow(() -> new IllegalArgumentException("조회하는 아이디가 없습니다"));
        return "redirect:/members/member/{memberId}";
    }


    /**
     * 테스트용 멤버 넣어두기
     */
    @PostConstruct
    public void init() {
        memberRepository.save(new Member("A",20,"aa@gmail.com", FEMALE, 167, 55));
        memberRepository.save(new Member("B",56,"bb@gmail.com", MALE, 187, 70));
        memberRepository.save(new Member("C",34,"cc@gmail.com", FEMALE, 155, 50));
    }
    //String userName, int age, String email, Gender gender, int height, int weight
}
