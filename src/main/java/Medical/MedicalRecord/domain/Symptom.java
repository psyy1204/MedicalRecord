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
    @Column
    private int bodyTemperature;

    //맥박
    @Column
    private int pulse;

    //혈압
    @Column
    private String bloodPressure;
    //120/80 이런식으로 입력이 되어야하는데 String으로 하는게 맞는지 의문ㅜㅜ

    //산소포화도
    @Column
    private int oxygenSaturation;

    //호흡수
    @Column
    private int respirationRate;

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
