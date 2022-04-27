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

    /**
     * 회원이름
     */
    @Column(nullable = false)
    private String userName;

    /**
     * 별칭
     */
    @Column(nullable = false)
    private String nickName;

    /**
     * 이메일
     */
    @Column(nullable = false)
    private String email;

    /**
     * 회원나이
     */
    private Integer age;

    /**
     * 성별
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * 키
     */
    private Integer height;

    /**
     * 몸무게
     */
    private Integer weight;

    /**
     * 생성 날짜
     */
    @Column(nullable = false)
    private LocalDateTime createdDate;

    /**
     * 수정 날짜
     */
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @Builder
    public Member(String userName, String nickName, Integer age,
                  String email,Gender gender, Integer height, Integer weight) {
        this.userName = userName;
        this.nickName = nickName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
