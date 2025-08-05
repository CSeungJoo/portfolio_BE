package kr.cseungjoo.projectserver.repository;

import kr.cseungjoo.projectserver.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(long userId);

    boolean existsByIdAndUserId(long projectId, long userId);
}
