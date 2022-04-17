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
    private Float bodyTemperature;

    //맥박
    private Integer pulse;

    //수축기압
    private Integer systolic;
    //이완기
    private Integer diastolic;

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
    public Symptom(String simpleSymptom, String detailSymptom, Date startDate, Float bodyTemperature,
                   Integer pulse, Integer systolic,Integer diastolic, Integer oxygenSaturation) {
        this.simpleSymptom = simpleSymptom;
        this.detailSymptom = detailSymptom;
        this.startDate = startDate;
        this.bodyTemperature = bodyTemperature;
        this.pulse = pulse;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.oxygenSaturation = oxygenSaturation;
    }

    public String startDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(startDate);
    }
}
