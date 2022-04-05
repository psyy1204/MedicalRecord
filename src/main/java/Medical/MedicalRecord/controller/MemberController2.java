package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/members")
public class MemberController2 {

    //@Autowired
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
    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable("membeId") Long id) {
        return  memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("조회하는 아이디가 없습니다"));
    }

    /**
     * 수정
     */
    @PutMapping("/{memberId}")
    public void updateMember(@PathVariable("memberId") Long id, @RequestBody Member newMember) {
        memberRepository.findById(id)
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

    }

    /**
     * 삭제
     */
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable("memberId") Long id){
        memberRepository.deleteById(id);
    }
}
