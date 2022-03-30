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
public class MedicalRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_id;

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

    @Builder
    public MedicalRecord(String doctor_name, String diagnosis,
                         String etc, int price, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.doctor_name = doctor_name;
        this.diagnosis = diagnosis;
        this.etc = etc;
        this.price = price;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
