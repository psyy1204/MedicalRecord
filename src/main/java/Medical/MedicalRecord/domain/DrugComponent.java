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
    @Column(name="component_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // 약성분명
    private String componentName;

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
}
