package Medical.MedicalRecord.service;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import Medical.MedicalRecord.form.MemberForm;
import Medical.MedicalRecord.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);// 중복확인
        memberRepository.save(member);
        return member.getMemberId();
    }

    /**
     * 중복회원 검사
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     */
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    public void editMember(Long memberId, String userName ,Integer age, Gender gender, Integer height, Integer weight){
        Member newMember = memberRepository.findById(memberId);
        newMember.setAge(age);
        newMember.setUserName(userName);
        newMember.setGender(gender);
        newMember.setHeight(height);
        newMember.setWeight(weight);
        newMember.setUpdatedDate(LocalDateTime.now());
    }

    /**
     * 삭제
     */
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }

    /**
     * 일부분 수정
     */
    @Transactional
    public void updateMember(MemberForm form, Long id) {
        if (form == null) return;
        Member findMember = memberRepository.findById(id);
        findMember.setUpdatedDate(LocalDateTime.now());
        findMember.updateMember(form);
    }

    public int findAllCount() {
        return memberRepository.findAllCount();
    }

    public List<Member> findListPaging(int startIndex, int pageSize){
        return memberRepository.findListPaging(startIndex, pageSize);
    }
}
