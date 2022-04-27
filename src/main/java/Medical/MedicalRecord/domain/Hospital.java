package Medical.MedicalRecord.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hospitalId;


    /**
     *  병원명
     */
    private String hospitalName;

    /**
     *  병원주소
     */
    private String hospitalAddress;

    /**
     *  병원 연락처
     */
    private Integer hospitalContact;

    @Builder
    public Hospital(String hospitalName, String hospitalAddress, Integer hospitalContact) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalContact = hospitalContact;
    }
}
