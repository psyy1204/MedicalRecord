package Medical.MedicalRecord.form;

import Medical.MedicalRecord.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

//데이터를 전달받을 폼객체
@Getter @Setter
@NoArgsConstructor
public class MemberForm {

    private Long id;
    @NotBlank(message = "회원 이름은 필수 입니다")
    private String username;

    @NotEmpty(message = "메일은 필수 입니다")
    @Email
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;

    @NotEmpty(message = "비밀번호확인은 필수 입니다")
    private String passwordCheck;

    @NotBlank(message = "닉네임은 필수 입니다")
    private String nickName;

    private Integer age;
    private Gender gender;
    private Integer height;
    private Integer weight;
    private LocalDateTime updateDate;

}
