package kr.cseungjoo.projectserver.service;

import kr.cseungjoo.projectserver.domain.Project;
import kr.cseungjoo.projectserver.domain.ProjectStatus;
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
    public Project create(String title, String summary, String description, List<String> techStack, ProjectStatus status, LocalDate startAt, LocalDate endAt, String github, String prod, String imageUrl, long userId) {
        Project build = Project.builder()
                .title(title)
                .summary(summary)
                .description(description)
                .techStack(techStack)
                .status(status)
                .startAt(startAt)
                .endAt(endAt)
                .github(github)
                .prod(prod)
                .imageUrl(imageUrl)
                .userId(userId)
                .build();

        Project save = projectRepository.save(build);

        return save;
    }

    @Transactional
    public Project modify(long projectId, String title, String summary, String description, List<String> techStack, ProjectStatus status, LocalDate startAt, LocalDate endAt, String github, String prod, String imageUrl) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ProjectNotFoundException::new
        );

        project.modify(title, summary, description, techStack, status, startAt, endAt, github, prod, imageUrl);

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
