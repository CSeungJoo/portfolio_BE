package kr.cseungjoo.skillserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int proficiencyRate;

    @Column
    private SkillLevel skillLevel;

    @Column(columnDefinition = "TEXT")
    private String imgUrl;

    @Column
    private long userId;

    public void modify(String name, int proficiencyRate, SkillLevel skillLevel, String imgUrl) {
        this.name = name;
        this.proficiencyRate = proficiencyRate;
        this.skillLevel = skillLevel;
        this.imgUrl = imgUrl;
    }
}
