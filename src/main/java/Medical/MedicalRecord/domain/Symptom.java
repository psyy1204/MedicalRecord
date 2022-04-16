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

    //증상기입
    @Column(nullable = false)
    private String symptom;

    //체온
    private Integer bodyTemperature;

    //맥박
    private Integer pulse;

    //혈압
    private Integer bloodPressure;
    //120/80

    //산소포화도
    private Integer oxygenSaturation;

    //호흡수
    private Integer respirationRate;

    //생성날짜
    @Column(nullable = false)
    private LocalDateTime createdDate;

    //수정날짜
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToOne
    @JoinColumn(name = "recordId")
    private MedicalRecord medicalRecord;

    @Builder
    public Symptom(String symptom, Integer bodyTemperature, Integer pulse, Integer bloodPressure, Integer oxygenSaturation,
                   int respirationRate, Member member, MedicalRecord medicalRecord) {
        this.symptom = symptom;
        this.bodyTemperature = bodyTemperature;
        this.pulse = pulse;
        this.bloodPressure = bloodPressure;
        this.oxygenSaturation = oxygenSaturation;
        this.respirationRate = respirationRate;
        this.member = member;
        this.medicalRecord = medicalRecord;
    }
}
