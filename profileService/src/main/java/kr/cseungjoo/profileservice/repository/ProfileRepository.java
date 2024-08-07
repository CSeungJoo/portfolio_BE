package kr.cseungjoo.profileservice.repository;

import kr.cseungjoo.profileservice.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
