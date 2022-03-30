package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
public class DrugComponent {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long component_id;

    @Column(nullable = false)
    private String component_name;                      //약성분

    public void setComponent_name(String component_name) {
        this.component_name = component_name;
    }
}
