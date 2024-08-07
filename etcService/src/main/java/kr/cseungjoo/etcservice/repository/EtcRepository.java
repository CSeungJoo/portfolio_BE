package kr.cseungjoo.etcservice.repository;

import kr.cseungjoo.etcservice.domain.Etc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtcRepository extends JpaRepository<Etc, Long> {
    List<Etc> getAllByUsersId(long userId);
}
