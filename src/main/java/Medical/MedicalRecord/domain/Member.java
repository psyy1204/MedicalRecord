package Medical.MedicalRecord.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    // 회원이름
    private String userName;

    @Column//(nullable = false)
    // 이메일
    private String email;

    @Column
    // 회원나이
    private Integer age;

    @Enumerated(EnumType.STRING)
    // 회원성별
    private Gender gender;

    @Column
    // 키
    private Integer height;

    @Column
    // 몸무게
    private Integer weight;


    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;  //생성날짜

    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate;  //수정날짜

    @Builder
    public Member(String userName, int age, String email, Gender gender, int height, int weight) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
}
