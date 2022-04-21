package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
public class PrescriptionDrug {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionDrugId;

    //처방기간
    private Date durationStart;
    private Date durationEnd;

}
