package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Getter
@Entity
@NoArgsConstructor
public class DrugAllergy {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergy_id;

}
