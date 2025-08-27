package kr.cseungjoo.awardserver.dto;

import kr.cseungjoo.awardserver.model.AwardModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnAwardDto {
    private long awardId;
    private String name;
    private String organization;
    private String awardRank;
    private LocalDate awardedAt;

    public ReturnAwardDto(AwardModel awardModel) {
        this.awardId = awardModel.getId();
        this.name = awardModel.getName();
        this.organization = awardModel.getOrganization();
        this.awardRank = awardModel.getAwardRank();
        this.awardedAt = awardModel.getAwardedAt();
    }
}