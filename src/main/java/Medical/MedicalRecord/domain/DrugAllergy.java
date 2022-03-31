package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Getter
@Entity
@NoArgsConstructor
public class DrugAllergy {

    @Column(name = "allergy_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    // 일대다(약 알러지 1- 약 성분 여러개)
    private List<DrugComponent> drugComponentList = new ArrayList<>();

    //member는 member에서 가져다??
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
