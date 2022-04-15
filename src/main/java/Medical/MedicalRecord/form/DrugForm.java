package Medical.MedicalRecord.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class DrugForm {

    private Long id;

    @NotEmpty(message = "성분명은 필수 입니다")
    private String componentName;
}
