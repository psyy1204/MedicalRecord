package Medical.MedicalRecord.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class SymptomForm {

    private Long id;
    @NotEmpty(message = "증상은 필수 입니다")
    @Size(max = 20)
    private String simpleSymptom;
    private String detailSymptom;
    //소수점, 범위 지정해주기
    @Digits(integer = 2,fraction = 1)
    private Float bodyTemperature;
    @Positive
    private Integer pulse;
    //수축기압이랑 이완기압 따로 만들기, 범위 지정
    private Integer systolic;
    private Integer diastolic;

    //100 이내로 제한 95 밑으로 떨어지면 빨간색?
    private Integer oxygenSaturation;
    private LocalDateTime updateDate;
    @Past(message = "이전날짜만 입력 가능합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    private Long medicalRecordId;

}
