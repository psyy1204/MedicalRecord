package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table
@NoArgsConstructor
@Getter
public class PrescriptionDrug {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionDrugId;
}
