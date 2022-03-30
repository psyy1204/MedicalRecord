package Medical.MedicalRecord.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(nullable = false)
    private String userName;        //회원이름

    @Column//(nullable = false)
    private String email;        //회원이름

    @Column
    private int age;                //회원나이

    @Column
    private String sex;                //회원성별

    @Column
    private int height;             //회원 키

    @Column
    private int weight;             //회원 몸무게

    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;  //생성날짜

    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate;  //수정날짜

//    @OneToOne(fetch = FetchType.LAZY)
//    //객체가 실제 사용할때 조회
//    private DrugInfo drugInfo;

    @Builder
    public Member(String userName, int age, String email, String sex, int height, int weight, LocalDateTime createdDate,
                  LocalDateTime updatedDate) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
