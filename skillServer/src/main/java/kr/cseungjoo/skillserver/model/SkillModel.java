package kr.cseungjoo.skillserver.model;

import kr.cseungjoo.skillserver.domain.Skill;
import kr.cseungjoo.skillserver.domain.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillModel {
    private Long id;
    private String name;
    private int proficiencyRate;
    private SkillLevel skillLevel;
    private String imgUrl;
    private long userId;

    public SkillModel(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.proficiencyRate = skill.getProficiencyRate();
        this.skillLevel = skill.getSkillLevel();
        this.imgUrl = skill.getImgUrl();
        this.userId = skill.getUserId();
    }
}
