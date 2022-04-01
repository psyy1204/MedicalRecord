package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hospitalId;

    @Column
    // 병원명
    private String hospitalName;

    @Column
    // 병원주소
    private String hospitalAddress;

    @Column
    // 연락처
    private String hospitalContact;

    @Builder
    public HospitalInfo(String hospitalName, String hospitalAddress, String hospitalContact) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }
}
