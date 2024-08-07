package kr.cseungjoo.linkService.repository;

import kr.cseungjoo.linkService.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> getAllByUsersId(long usersId);
}
