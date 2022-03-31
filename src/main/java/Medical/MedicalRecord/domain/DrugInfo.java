package Medical.MedicalRecord.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DrugInfo {

    @Id
    @Column (name = "drug_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // 약명
    private String drugName;

    @OneToOne
    @JoinColumn(name = "component_id")
    private DrugComponent drugComponent;

    @Column
    public void setDrugName(String drug_name) {
        this.drugName = drug_name;
    }

    @Builder
    public DrugInfo(String drug_name) {
        this.drugName = drug_name;
    }
}
