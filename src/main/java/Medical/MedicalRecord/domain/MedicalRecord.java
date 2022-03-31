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
    private Long record_id;

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
    private LocalDateTime created_date;

    @Column(nullable = false)
    // 수정일
    private LocalDateTime updated_date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalInfo hospitalInfo;

    @OneToOne
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;

    // 처방약
}
