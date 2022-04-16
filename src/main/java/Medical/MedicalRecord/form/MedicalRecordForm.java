package Medical.MedicalRecord.form;

import Medical.MedicalRecord.domain.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class MedicalRecordForm {

    private Long id;
    private String doctorName;
    @NotEmpty(message = "진단명은 필수 입니다")
    private String diagnosis;
    private String medicalDepartmentCode;
    private String etc;
    private Integer price;
    @Past(message = "이전날짜만 입력 가능합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitedDate;
    @Future(message = "이후날짜만 입력 가능합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextVisitDate;
    private LocalDateTime updateDate;

    private String hospitalName;
}




