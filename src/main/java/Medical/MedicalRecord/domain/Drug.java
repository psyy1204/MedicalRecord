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

    /**
     * 약 제품명
     */
    @Column(nullable = false)
    private String drugName;

    /**
     * 약 성분명
     */
    private String drugComponent;

    @Builder
    public Drug(String drugName, String drugComponent) {
        this.drugName = drugName;
        this.drugComponent = drugComponent;
    }
}
