package kr.cseungjoo.awardserver.model;

import kr.cseungjoo.awardserver.domain.Award;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardModel {
    private Long id;
    private String name;
    private String organization;
    private String awardRank;
    private LocalDate awardedAt;
    private long userId;

    public AwardModel(Award award) {
        this.id = award.getId();
        this.name = award.getName();
        this.organization = award.getOrganization();
        this.awardRank = award.getAwardRank();
        this.awardedAt = award.getAwardedAt();
        this.userId = award.getUserId();
    }
}