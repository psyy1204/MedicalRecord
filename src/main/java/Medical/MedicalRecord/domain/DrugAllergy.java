package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
public class DrugAllergy {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;

    @OneToMany
    // 일대다(약 알러지 1- 약 성분 여러개)
    private List<DrugComponent> drugComponentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder

    public DrugAllergy(List<DrugComponent> drugComponentList, Member member) {
        this.drugComponentList = drugComponentList;
        this.member = member;
    }
}
