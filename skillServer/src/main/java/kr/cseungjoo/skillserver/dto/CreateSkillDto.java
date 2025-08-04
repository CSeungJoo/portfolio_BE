package kr.cseungjoo.skillserver.dto;

import kr.cseungjoo.skillserver.domain.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSkillDto {
    private String name;
    private SkillLevel skillLevel;
    private int proficiencyRate;
    private String imgUrl;
}
