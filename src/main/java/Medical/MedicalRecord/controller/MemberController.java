package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 생성
     */
    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody Member member){
        Member saveMember = memberRepository.save(member);
        return ResponseEntity.ok(saveMember);
    }

    /**
     * 전체 사용자 목록 조회
     */
    @GetMapping("/list")
    public List<Member> getMemberList(){
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    /**
     * 아이디로 조회
     */
    @GetMapping("/{member_id}")
    public Member getMember(@PathVariable("member_id") Long id) {
        return  memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("조회하는 아이디가 없습니다"));
    }

    /**
     * 수정
     */
    @PutMapping("/{member_id}")
    public void updateMember(@PathVariable("member_id") Long id, @RequestBody Member newMember) {
        memberRepository.findById(id)
                .map(member -> {
                    member.setUserName(newMember.getUserName());
                    member.setAge(newMember.getAge());
                    member.setEmail(newMember.getEmail());
                    return memberRepository.save(member);
                })
                .orElseGet(() -> {
                    newMember.setMember_id(id);
                    return memberRepository.save(newMember);
                });
    }

    /**
     * 등록
     */
    @DeleteMapping("/{member_id}")
    public void deleteMember(@PathVariable("member_id") Long id){
        memberRepository.deleteById(id);
    }
}
