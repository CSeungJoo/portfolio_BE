package kr.cseungjoo.userserver.repository;

import kr.cseungjoo.userserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u.id from User u where u.email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);

    @Query("select u.id from User u where u.nickname = :nickname")
    Optional<Long> findIdByNickname(@Param("nickname") String nickname);
}
