package kr.cseungjoo.awardserver.service;

import kr.cseungjoo.awardserver.domain.Award;
import kr.cseungjoo.awardserver.exception.AwardNotFoundException;
import kr.cseungjoo.awardserver.repository.AwardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AwardService {

    private final AwardRepository awardRepository;

    @Transactional
    public Award create(String name, String organization, String awardRank, LocalDate awardAt, long userId) {
        Award build = Award.builder()
                .name(name)
                .organization(organization)
                .awardRank(awardRank)
                .awardedAt(awardAt)
                .userId(userId)
                .build();

        Award save = awardRepository.save(build);

        return save;
    }

    public List<Award> findAll(long userId) {
        List<Award> awardList = awardRepository.findAllByUserId(userId);

        return awardList;
    }

    public Award getById(long awardId) {
        Award award = awardRepository.findById(awardId).orElseThrow(
                AwardNotFoundException::new
        );

        return award;
    }

    public Award save(Award award) {
        Award save = awardRepository.save(award);

        return save;
    }

    public void remove(Award award) {
        awardRepository.delete(award);
    }
}