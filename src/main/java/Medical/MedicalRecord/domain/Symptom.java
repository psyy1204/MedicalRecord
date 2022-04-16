package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Symptom {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long symptomId;

    //증상기입
    @Column(nullable = false)
    private String simpleSymptom;

    private String detailSymptom;

    //증상 시작일
    private Date startDate;

    //체온
    private Integer bodyTemperature;

    //맥박
    private Integer pulse;

    //혈압
    private Integer bloodPressure;
    //120/80

    //산소포화도
    private Integer oxygenSaturation;

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
    public Symptom(String simpleSymptom, String detailSymptom, Date startDate, Integer bodyTemperature, Integer pulse, Integer bloodPressure, Integer oxygenSaturation) {
        this.simpleSymptom = simpleSymptom;
        this.detailSymptom = detailSymptom;
        this.startDate = startDate;
        this.bodyTemperature = bodyTemperature;
        this.pulse = pulse;
        this.bloodPressure = bloodPressure;
        this.oxygenSaturation = oxygenSaturation;
    }

    public String startDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(startDate);
    }
}
