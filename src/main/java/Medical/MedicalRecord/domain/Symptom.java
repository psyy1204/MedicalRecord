package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Symptom {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomId;

    @Column(nullable = false)
    private String symptom;                 //증상기입

    @Column
    private int bodyTemperature;           //체온

    @Column
    private int pulse;                      //맥박

    @Column
    private String bloodPressure;          //혈압

    @Column
    private int oxygenSaturation;          //산소포화도

    @Column
    private int respirationRate;           //호흡수

    @Column(nullable = false)
    private LocalDateTime createdDate;      //생성날짜

    @Column(nullable = false)
    private LocalDateTime updatedDate;      //수정날짜

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToOne
    @JoinColumn(name = "recordId")
    private MedicalRecord medicalRecord;

    @Builder
    public Symptom(String symptom, int bodyTemperature, int pulse, String bloodPressure, int oxygenSaturation,
                   int respirationRate, Member member, MedicalRecord medicalRecord) {
        this.symptom = symptom;
        this.bodyTemperature = bodyTemperature;
        this.pulse = pulse;
        this.bloodPressure = bloodPressure;
        this.oxygenSaturation = oxygenSaturation;
        this.respirationRate = respirationRate;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.member = member;
        this.medicalRecord = medicalRecord;
    }
}
