package kr.cseungjoo.awardserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String organization;

    @Column
    private String awardRank;

    @Column
    private LocalDate awardedAt;

    @Column
    private long userId;

    public void modify(String name, String organization, String awardRank, LocalDate awardedAt) {
        this.name = name;
        this.organization = organization;
        this.awardRank = awardRank;
        this.awardedAt = awardedAt;
    }
}