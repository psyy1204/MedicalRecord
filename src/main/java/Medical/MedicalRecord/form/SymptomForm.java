package Medical.MedicalRecord.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@NoArgsConstructor
public class SymptomForm {

    private Long id;

    @NotEmpty(message = "증상은 필수 입니다")
    private String symptom;
    private Integer bodyTemperature;
    private Integer pulse;
    private Integer bloodPressure;
    private Integer oxygenSaturation;
    private Integer respirationRate;
}
