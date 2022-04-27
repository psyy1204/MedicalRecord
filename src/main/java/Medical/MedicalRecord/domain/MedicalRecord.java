package Medical.MedicalRecord.domain;

import Medical.MedicalRecord.form.MedicalRecordForm;
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

    /**
     *진료의사 성명
     */
    private String doctorName;

    /**
     *진단명
     */
    @Column(nullable = false)
    private String diagnosis;

    /**
     *진료과
     */
    private String medicalDepartmentCode;

    /**
     * 기타 특이사항
     */
    private String etc;

    /**
     * 진료비
     */
    private Integer price;

    /**
     * 진료일자
     */
    private Date visitedDate;

    /**
     * 진료일 포맷
     */
    public String visitedDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(visitedDate);
    }

    /**
     * 다음 진료예정일자
     */
    private Date nextVisitDate;

    /**
     * 다음 진료예정일자 포맷
     */
    public String nextVisitDateString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(nextVisitDate);
    }

    /**
     * 증상 입력 여부
     */
    private boolean hasSymptom;

    /**
     * 처방 입력 여부
     */
    private boolean hasDrug;

    /**
     * 기록 등록일
     */
    @Column(nullable = false)
    private LocalDateTime createdDate;

    /**
     * 기록 수정일
     */
    @Column(nullable = false)
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
