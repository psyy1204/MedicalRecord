package Medical.MedicalRecord.domain;

import lombok.Builder;
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
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column
    // 의사이름
    private String doctorName;

    @Column(nullable = false)
    // 진단명
    private String diagnosis;

    @Column
    // 기타사항
    private String etc;

    @Column
    // 진료비
    private int price;

    @Column(nullable = false)
    // 등록일
    private LocalDateTime createdDate;

    @Column(nullable = false)
    // 수정일
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private HospitalInfo hospitalInfo;

    @OneToOne
    @JoinColumn(name = "symptomId")
    private Symptom symptom;

    // 처방약

    @Builder
    public MedicalRecord(String doctorName, String diagnosis, String etc, int price,
                         Member member, HospitalInfo hospitalInfo, Symptom symptom) {
        this.recordId = recordId;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.etc = etc;
        this.price = price;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.member = member;
        this.hospitalInfo = hospitalInfo;
        this.symptom = symptom;
    }
}
