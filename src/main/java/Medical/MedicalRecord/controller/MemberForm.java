package Medical.MedicalRecord.controller;

import Medical.MedicalRecord.domain.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

//데이터를 전달받을 폼객체
@Getter @Setter
public class MemberForm {

    private Long id;
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String username;

    @NotEmpty(message = "메일은 필수 입니다")
    @Email
    private String email;

    private Integer age;
    private Gender gender;
    private Integer height;
    private Integer weight;

}
