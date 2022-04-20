package Medical.MedicalRecord.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Drug {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugId;

    @Column(nullable = false)
    // 약명
    private String drugName;

    @ManyToOne
    @JoinColumn(name = "prescriptionDrugId")
    private PrescriptionDrug prescriptionDrug;

    @Builder
    public Drug(String drugName) {
        this.drugName = drugName;
    }
}
