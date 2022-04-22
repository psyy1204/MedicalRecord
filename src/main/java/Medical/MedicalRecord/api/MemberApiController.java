package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;
import Medical.MedicalRecord.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    /**
     * @return 전체 회원값
     */
    @GetMapping
    public List<Member> members() {
        return memberService.findAll();
    }

    /**
     * @param id
     * @return 해당하는 멤버
     */
    @GetMapping("/{memberId}")
    public Member member(@PathVariable("memberId") Long id,
                         HttpServletResponse response) throws IOException {
        Member member = memberService.findById(id);
        if(member == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 회원이 없습니다");
            return null;
        } else {
            return member;
        }
    }

    /**
     *  회원등록
     */
    @PostMapping
    public void addMember(@RequestBody MemberForm form,
                          HttpServletResponse response) throws IOException{

        if (form.getUsername() == null || form.getEmail()==null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "이름과 이메일은 필수입니다");
        }else{
            Member member = new Member();
            member.setAge(form.getAge());
            member.setEmail(form.getEmail());
            member.setUserName(form.getUsername());
            member.setNickName(form.getNickName());
            member.setHeight(form.getHeight());
            member.setWeight(form.getWeight());
            member.setGender(form.getGender());
            member.setCreatedDate(LocalDateTime.now());
            member.setUpdatedDate(LocalDateTime.now());
            memberService.join(member);
        }
    }

    /**
     *
     * @param id
     * 회원 정보 수정
     */
    @PatchMapping("/{memberId}")
    public void editMember(@PathVariable("memberId") Long id,
                             @RequestBody MemberForm form,
                             HttpServletResponse response) throws IOException{
        if(memberService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 회원이 없습니다.");
        } else {
            memberService.updateMember(form, id);
        }
    }

    /**
     * @param id
     * 회원삭제
     */
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long id,
                             HttpServletResponse response) throws IOException{
        if(memberService.findById(id) == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"찾는 회원이 없습니다.");
        }else {
            memberService.deleteMember(id);
        }
    }


}
