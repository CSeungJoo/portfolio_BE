package kr.cseungjoo.projectserver.service;

import kr.cseungjoo.projectserver.domain.Project;
import kr.cseungjoo.projectserver.exception.ProjectNotFoundException;
import kr.cseungjoo.projectserver.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAllByUserId(long userId) {
        List<Project> projectList = projectRepository.findAllByUserId(userId);

        return projectList;
    }

    @Transactional
    public Project create(String title, String summary, String description, List<String> techStack, LocalDate startAt, LocalDate endAt, long userId) {
        Project build = Project.builder()
                .title(title)
                .summary(summary)
                .description(description)
                .techStack(techStack)
                .startAt(startAt)
                .endAt(endAt)
                .userId(userId)
                .build();

        Project save = projectRepository.save(build);

        return save;
    }

    @Transactional
    public Project modify(long projectId, String title, String summary, String description, List<String> techStack, LocalDate startAt, LocalDate endAt) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ProjectNotFoundException::new
        );

        project.modify(title, summary, description, techStack, startAt, endAt);

        Project save = projectRepository.save(project);

        return save;
    }

    @Transactional
    public void remove(long projectId) {
        projectRepository.deleteById(projectId);
    }

    public boolean isUserMemberOfProject(long projectId, long userId) {
        boolean exists = projectRepository.existsByIdAndUserId(projectId, userId);

        return exists;
    }
}
