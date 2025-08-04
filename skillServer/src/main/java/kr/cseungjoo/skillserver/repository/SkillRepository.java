package kr.cseungjoo.skillserver.repository;

import kr.cseungjoo.skillserver.domain.Skill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByUserId(long userId);

    Skill findByUserId(long userId);
}
