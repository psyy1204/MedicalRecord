package Medical.MedicalRecord.form;

import Medical.MedicalRecord.domain.Drug;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PrescriptionForm {
    private Long prescriptionId;

    @NotEmpty(message = "약명은 필수 입니다")
    private String drugName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date drugStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date drugEnd;

    //복용횟수
    private Integer dosesCount;

    private Long recordId;

    private Drug drug;
}
