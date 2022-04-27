package Medical.MedicalRecord.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor
public class HospitalForm {

    private Long hospitalId;

    @NotBlank(message = "병원 이름은 필수 입니다")
    private String hospitalName;

    private String hospitalAddress;

    @Digits(integer = 12, fraction = 0, message = "12자이내로 입력하세요")
    private Integer hospitalContact;
}
