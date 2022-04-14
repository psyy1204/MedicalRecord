package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "medicalrecord")
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
    //진료과
    private String medicalDepartmentCode;

    @Column
    // 기타사항
    private String etc;

    @Column
    // 진료비
    private int price;

    @Column
    //진료일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitedDate;

    // 진료일자 포맷변경
    public String visitedDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(visitedDate);
    }

    @Column
    //진료예정일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextVisitDate;

    // 진료예정일자 포맷변경
    public String nextVisitDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(nextVisitDate);
    }

    @Column(nullable = false)
    // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    // 수정일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate = LocalDateTime.now();

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
                         Member member, Hospital hospital, Symptom symptom,
                         Date visitedDate, Date nextVisitDate) {
        this.recordId = recordId;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.etc = etc;
        this.price = price;
        this.member = member;
        this.hospital = hospital;
        this.symptom = symptom;
        this.visitedDate = visitedDate;
        this.nextVisitDate = nextVisitDate;
    }

}
