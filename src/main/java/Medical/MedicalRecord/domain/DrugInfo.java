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

    @Column(nullable = false)                   //약이름
    private String drug_name;

    @OneToOne
    @JoinColumn(name = "component_id")
    private DrugComponent drugComponent;

    @Column
    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    @Builder
    public DrugInfo(String drug_name) {
        this.drug_name = drug_name;
    }
}
