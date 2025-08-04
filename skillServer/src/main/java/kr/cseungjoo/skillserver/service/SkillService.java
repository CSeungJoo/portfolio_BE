package kr.cseungjoo.skillserver.service;

import kr.cseungjoo.skillserver.domain.Skill;
import kr.cseungjoo.skillserver.domain.SkillLevel;
import kr.cseungjoo.skillserver.exception.SkillNotFoundException;
import kr.cseungjoo.skillserver.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    @Transactional
    public Skill create(String name, int proficiencyRate, SkillLevel skillLevel, String imgUrl, long userId) {
        Skill build = Skill.builder()
                .name(name)
                .proficiencyRate(proficiencyRate)
                .skillLevel(skillLevel)
                .imgUrl(imgUrl)
                .userId(userId)
                .build();

        Skill save = skillRepository.save(build);

        return save;
    }

    public List<Skill> findAll(long userId) {
        List<Skill> skillList = skillRepository.findAllByUserId(userId);

        return skillList;
    }

    public Skill getById(long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(
                SkillNotFoundException::new
        );

        return skill;
    }

    public Skill save(Skill skill) {
        Skill save = skillRepository.save(skill);

        return save;
    }

    public void remove(Skill skill) {
        skillRepository.delete(skill);
    }
}
