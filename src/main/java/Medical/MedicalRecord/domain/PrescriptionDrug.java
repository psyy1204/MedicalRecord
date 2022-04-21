package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
public class PrescriptionDrug {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionDrugId;

    //처방기간
    private Date durationStart;

    public String durationStartString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(durationStart);
    }
    private Date durationEnd;

    public String durationEndString(){
        return new SimpleDateFormat("yyyy-MM-dd").format(durationEnd);
    }

    //하루 복용횟수
    private Integer dosesCount;

    @ManyToOne
    @JoinColumn(name = "drugId")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "recordId")
    private MedicalRecord medicalRecord;

}
