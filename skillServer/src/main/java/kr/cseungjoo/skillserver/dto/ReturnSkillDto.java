package kr.cseungjoo.skillserver.dto;

import kr.cseungjoo.skillserver.domain.Skill;
import kr.cseungjoo.skillserver.domain.SkillLevel;
import kr.cseungjoo.skillserver.model.SkillModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnSkillDto {
    private long skillId;
    private String name;
    private SkillLevel skillLevel;
    private int proficiencyRate;
    private String imgUrl;

    public ReturnSkillDto(SkillModel skillModel) {
        this.skillId = skillModel.getId();
        this.name = skillModel.getName();
        this.proficiencyRate = skillModel.getProficiencyRate();
        this.skillLevel = skillModel.getSkillLevel();
        this.imgUrl = skillModel.getImgUrl();
    }
}
