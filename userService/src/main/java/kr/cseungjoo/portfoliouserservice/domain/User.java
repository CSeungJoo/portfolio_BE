package kr.cseungjoo.portfoliouserservice.domain;

import jakarta.persistence.*;
import kr.cseungjoo.portfoliouserservice.convert.StringListConverter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String career;

    @Column
    private String phone;

    @Column
    private LocalDate birth;

    @Column
    private UUID selectId;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<Integer> jobCategory_id;

    @PrePersist
    private void init() {
        selectId = UUID.randomUUID();
    }
}
