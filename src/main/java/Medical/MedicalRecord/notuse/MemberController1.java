//package Medical.MedicalRecord.controller;
//
//import Medical.MedicalRecord.domain.Member;
//import Medical.MedicalRecord.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//
//@Slf4j
//@Controller
//@RequestMapping("/mambers")
//public class MemberController {
//
//    @Autowired
//    private MemberRepository memberrepository;
//    //private final MemberService memberService;
//    private static Long sequence = 0L;
//
//
////    public MemberController(MemberService memberService) {
////        this.memberService = memberService;
////    }
//
////    @GetMapping(value = "/new")
////    public String createForm() {
////        return "members/createMemberForm";
////    }
//
////    //회원 실제 등록
////    @PostMapping(value = "/new")
////    public String create(MemberForm form) {
////
////        Member member = new Member();
////        member.setMember_id(++sequence);
////        member.setUserName(form.getName());
////        member.setAge(form.getAge());
////
////        memberService.join(member);
////
////        return "redirect:/";
////        //처음화면으로 돌아감
////    }
//
//
//
////    //회원 조회
////    @GetMapping
////    public String list(Model model) {
////        List<Member> members = memberService.findMembers();
////        model.addAttribute("members", members);
////        return "members/memberList";
////    }
//
////    @Autowired
////    private MemberRepository memberRepository;
////
////    @PostMapping("members")
////    public Member join(@RequestBody Member member){
////        Member joinMember = memberRepository.save(member);
////        return joinMember;
////    }
////
////    @GetMapping("members")
////    public List<Member> listMember() {
////        List<Member> list = new ArrayList<>();
////        Iterable<Member> iterator = memberRepository.findAll();
////        for (Member member : iterator) {
////            list.add(member);
////        }
////        return list;
////    }
////
////    @PutMapping("members/{member_id}")
////    public Member updateMember(@PathVariable("member_id")Long member_id,
////                               @RequestBody Member member) {
////        member.setMember_id(member_id);
////        Member updateMember = memberRepository.save(member);
////        return updateMember;
////    }
////
////    @DeleteMapping("members/{member_id}")
////    public void deleteMember(@PathVariable("member_id")Long member_id) {
////        memberRepository.deleteById(member_id);
////    }
//}
