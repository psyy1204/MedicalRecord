package Medical.MedicalRecord.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter @Setter
public class DrugComponent {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long componentId;

    @Column(nullable = false)
    // 약성분명
    private String componentName;

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public DrugComponent(String componentName) {
        this.componentName = componentName;
    }
}
