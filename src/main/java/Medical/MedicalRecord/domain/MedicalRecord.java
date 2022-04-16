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
public class MedicalRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    // 의사이름
    private String doctorName;

    @Column(nullable = false)
    // 진단명
    private String diagnosis;

    //진료과
    private String medicalDepartmentCode;

    // 기타사항
    private String etc;

    // 진료비
    private Integer price;

    //진료일자
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitedDate;

    // 진료일자 포맷변경
    public String visitedDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(visitedDate);
    }

    //진료예정일자
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextVisitDate;

    // 진료예정일자 포맷변경
    public String nextVisitDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(nextVisitDate);
    }

    //증상이 입력되어있는지 여부
    private boolean hasSymptom;

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
                         Member member, Hospital hospital, String medicalDepartmentCode, Symptom symptom,
                         Date visitedDate, Date nextVisitDate) {
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.etc = etc;
        this.price = price;
        this.medicalDepartmentCode = medicalDepartmentCode;
        this.member = member;
        this.hospital = hospital;
        this.symptom = symptom;
        this.visitedDate = visitedDate;
        this.nextVisitDate = nextVisitDate;
    }

}
