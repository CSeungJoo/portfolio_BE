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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAllByUserId(long userId) {
        List<Project> projectList = projectRepository.findAllByUserIdOrderBySortOrderAsc(userId);

        return projectList;
    }

    @Transactional
    public Project create(String title, String summary, String description, List<String> techStack, ProjectStatus status, LocalDate startAt, LocalDate endAt, String github, String prod, String imageUrl, long userId) {
        long nextSortOrder = projectRepository.findMaxSortOrderByUserId(userId) + 1;

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
                .sortOrder(nextSortOrder)
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

    public Optional<Project> find(long projectId, long userId) {
        Optional<Project> projectOpt = projectRepository.findByIdAndUserId(projectId, userId);

        return projectOpt;
    }

    @Transactional
    public Project reorder(Project project, long afterId, long userId) {
        long newSortOrder;

        if (afterId == 0) {
            // 맨 앞으로 이동
            newSortOrder = 1;
            projectRepository.incrementSortOrderFrom(userId, 1);
        } else {
            // afterId 프로젝트 뒤로 이동
            Project afterProject = projectRepository.findByIdAndUserId(afterId, userId)
                    .orElseThrow(ProjectNotFoundException::new);
            newSortOrder = afterProject.getSortOrder() + 1;
            projectRepository.incrementSortOrderFrom(userId, newSortOrder);
        }

        project.setSortOrder(newSortOrder);
        return projectRepository.save(project);
    }
}
