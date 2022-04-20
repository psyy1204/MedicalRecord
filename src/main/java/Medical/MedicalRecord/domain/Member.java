package Medical.MedicalRecord.domain;

import Medical.MedicalRecord.form.MemberForm;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table
@NoArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long memberId;

    // 회원이름
    private String userName;

    // 이메일
    @Column(nullable = false)
    private String email;

    // 회원나이
    private Integer age;

    @Enumerated(EnumType.STRING)
    // 회원성별
    private Gender gender;

    // 키
    private Integer height;

    // 몸무게
    private Integer weight;

    @Column(nullable = false)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate; //생성날짜

    @Column(nullable = false)
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate; //수정날짜

    @Builder
    public Member(String userName, Integer age, String email,Gender gender, Integer height, Integer weight) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
