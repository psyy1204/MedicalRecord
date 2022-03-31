package Medical.MedicalRecord.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // 회원이름
    private String userName;

    @Column//(nullable = false)
    // 이메일
    private String email;

    @Column
    // 회원나이
    private int age;

    @Column
    // 회원성별
    private String gender;

    @Column
    // 키
    private int height;

    @Column
    // 몸무게
    private int weight;

    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;  //생성날짜

    @Column//(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate;  //수정날짜

    @Builder
    public Member(String userName, int age, String email, String gender, int height, int weight, LocalDateTime createdDate,
                  LocalDateTime updatedDate) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
