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

    @Column(name ="symptom_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symptom;                 //증상기입

    @Column
    private int body_temperature;           //체온

    @Column
    private int pulse;                      //맥박

    @Column
    private String blood_pressure;          //혈압

    @Column
    private int oxygen_saturation;          //산소포화도

    @Column
    private int respiration_rate;           //호흡수

    @Column(nullable = false)
    private LocalDateTime createdDate;      //생성날짜

    @Column(nullable = false)
    private LocalDateTime updatedDate;      //수정날짜

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "record_id")
    private MedicalRecord medicalRecord;

    @Builder
    public Symptom(String symptom, int body_temperature, int pulse, String blood_pressure,
                   int oxygen_saturation, int respiration_rate, LocalDateTime createdDate,
                   LocalDateTime updatedDate) {
        this.symptom = symptom;
        this.body_temperature = body_temperature;
        this.pulse = pulse;
        this.blood_pressure = blood_pressure;
        this.oxygen_saturation = oxygen_saturation;
        this.respiration_rate = respiration_rate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
