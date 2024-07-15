package kr.cseungjoo.portfoliouserservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
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
    private UUID selectId;

    @Column
    private Role role;

    @PrePersist
    private void init() {
        selectId = UUID.randomUUID();
    }
}
