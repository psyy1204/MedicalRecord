//package Medical.MedicalRecord.service;
//
//import Medical.MedicalRecord.domain.Member;
//import Medical.MedicalRecord.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    /**
//     * 회원가입
//     */
//    public Long join(Member member) {
//
//        //validateDuplicateMember(member); // 중복확인
//        memberRepository.save(member);
//        return member.getMember_id();
//    }
//
//    /**
//     * 중복회원 검사
//     */
////    private void validateDuplicateMember(Member member) {
////        memberRepository.findByEmail(member.getEmail())
////                .ifPresent(m -> {
////                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
////                });
////    }
//
//    /**
//     * 전체회원 조회
//     */
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }
////
////    /**
////     * 회원아이디로 회원조회
////     */
////    public Optional<Member> findOne(Long memberId) {
////        return memberRepository.findById(memberId);
////    }
//}
