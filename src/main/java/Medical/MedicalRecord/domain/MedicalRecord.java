package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Hospital hospital;

    @OneToOne
    @JoinColumn(name = "symptomId")
    private Symptom symptom;

    @ManyToOne
    @JoinColumn(name = "prescriptionDrugId")
    private PrescriptionDrug prescriptionDrug;

    @Builder
    public MedicalRecord(String doctorName, String diagnosis, String etc, int price,
                         Member member, Hospital hospital, Symptom symptom) {
        this.recordId = recordId;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.etc = etc;
        this.price = price;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.member = member;
        this.hospital = hospital;
        this.symptom = symptom;
    }
}
