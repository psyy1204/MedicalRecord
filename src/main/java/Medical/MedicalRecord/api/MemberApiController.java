package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;
import Medical.MedicalRecord.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    //1. DTO 클래스 생성해서 접근
//    @GetMapping("/members")
//    public List<MemberDto> members() {
//        List<Member> members = memberService.findAll();
//
//        return members.stream()
//                .map(m -> new MemberDto(m))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/members/{memberId}")
//    public MemberDto member(@PathVariable("memberId") Long id) {
//        Member member = memberService.findById(id);
//        return new MemberDto(member);
//    }
//
//    @PutMapping("/members/{memberId}")
//    public MemberResponse updateMember(
//            @PathVariable("memberId") Long memberId,
//            @RequestBody @Valid MemberRequest request) {
//
//        memberService.editMember(memberId, request.getUserName(), request.getAge(),
//                request.getGender(), request.getHeight(),request.getWeight());
//        Member findMember = memberService.findById(memberId);
//        return new MemberResponse(findMember.getMemberId(), findMember.getUserName(), findMember.getEmail(),
//                findMember.getAge(),findMember.getGender(),findMember.getHeight(),findMember.getWeight(), findMember.getUpdatedDate());
//    }

//    @Data
//    @AllArgsConstructor
//    static class MemberResponse {
//        private Long memberId;
//        private String userName;
//        private String email;
//        private Integer age;
//        private Gender gender;
//        private Integer height;
//        private Integer weight;
//        private LocalDateTime updatedDate;
//    }
//
//    @Data
//    static class MemberRequest {
//        private String userName;
//        private String email;
//        private Integer age;
//        private Gender gender;
//        private Integer height;
//        private Integer weight;
//        private LocalDateTime updatedDate;
//    }

//    @GetMapping("/members")
//    public ResponseEntity<List<Member>> members() {
//        List<Member> members = memberService.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(members);
//    }

    @GetMapping("/members")
    public List<Member> members() {
        return memberService.findAll();
    }

    @GetMapping("/members/{memberId}")
    public Member member(@PathVariable("memberId") Long id,
                         HttpServletResponse response) throws IOException {
        Member member = memberService.findById(id);
        if(member == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return member;
    }

    @PostMapping("/members")
    public void addMember(@RequestBody @Valid MemberForm form,
                          HttpServletResponse response) throws IOException{
        try{
            Member member = new Member(form.getUsername(),form.getAge(),form.getEmail(),
                    form.getGender(),form.getHeight(), form.getWeight());
            member.setCreatedDate(LocalDateTime.now());
            member.setUpdatedDate(LocalDateTime.now());
            Long memberId = memberService.join(member);
            response.setHeader("Location","/api/members/"+memberId);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }catch(IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_CONFLICT );
        }
    }

    @PutMapping("/members/{memberId}/edit")
    public Member editMember(@PathVariable("memberId") Long id,
                             @RequestBody MemberForm form,
                             HttpServletResponse response) throws IOException{
        if(memberService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        memberService.editMember(id, form.getUsername(),form.getAge(),form.getGender(),form.getHeight(),form.getWeight());
        return memberService.findById(id);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long id,
                             HttpServletResponse response) throws IOException{
        if(memberService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        memberService.deleteMember(id);
    }


}
