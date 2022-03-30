package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class HospitalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long hospital_id;

    @Column
    private String address;     //주소

    @Column
    private String contact;     //연락처
}
