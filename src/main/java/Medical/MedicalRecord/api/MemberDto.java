package Medical.MedicalRecord.api;

import Medical.MedicalRecord.domain.Gender;
import Medical.MedicalRecord.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private Long memberId;
    private String userName;
    private String email;
    private Integer age;
    private Gender gender;
    private Integer height;
    private Integer weight;
    private LocalDateTime createDate;
    private LocalDateTime updatedDate;

    public MemberDto(Member member) {
        memberId = member.getMemberId();
        userName = member.getUserName();
        email = member.getEmail();
        age = member.getAge();
        gender = member.getGender();
        height = member.getHeight();
        weight = member.getWeight();
        createDate = member.getCreatedDate();
        updatedDate = member.getUpdatedDate();
    }


}
