package kr.cseungjoo.awardserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditAwardDto {
    private long awardId;
    private String name;
    private String organization;
    private String awardRank;
    private LocalDate awardedAt;
}