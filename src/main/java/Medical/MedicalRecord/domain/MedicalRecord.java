package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class MedicalRecord {

    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String doctor_name;                     //의사이름

    @Column(nullable = false)
    private String diagnosis;                       //진단명

    @Column
    private String etc;                             //기타사항 입력

    @Column
    private int price;                              //비용

    @Column(nullable = false)
    private LocalDateTime createdDate;              //생성날짜

    @Column(nullable = false)
    private LocalDateTime updatedDate;              //수정날짜

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "hospita_id")
    private HospitalInfo hospitalInfo;

    @OneToOne
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;

    @OneToMany
    List<DrugInfo> drugInfoList = new ArrayList<>();

    //진료과
}
