package kr.cseungjoo.awardserver.repository;

import kr.cseungjoo.awardserver.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Long> {
    List<Award> findAllByUserId(long userId);
}