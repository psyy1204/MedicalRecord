package Medical.MedicalRecord.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicalDepartmentCode {

    private String code;
    private String displayName;

}
