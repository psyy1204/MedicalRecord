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
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long drugId;

    @Column(nullable = false)
    // 약명
    private String drugName;

    @OneToOne
    @JoinColumn(name = "componentId")
    private DrugComponent drugComponent;

    @Builder
    public DrugInfo(String drugName) {
        this.drugName = drugName;
    }
}
