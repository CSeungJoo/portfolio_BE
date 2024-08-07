package kr.cseungjoo.projectservice.repository;

import kr.cseungjoo.projectservice.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getAllByUsers_id(long users_id);
}
