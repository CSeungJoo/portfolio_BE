package kr.cseungjoo.userserver.domain;

import jakarta.persistence.*;
import kr.cseungjoo.commonmodule.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "users")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private LocalDate birth;

    @Column
    private int careerYears;

    @Column
    private String phoneNumber;

    @Column
    private int age;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
