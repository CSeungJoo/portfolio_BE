package kr.cseungjoo.projectserver.model;

import kr.cseungjoo.projectserver.domain.Project;
import kr.cseungjoo.projectserver.domain.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModel {
    private long id;
    private String title;
    private String summary;
    private String description;
    private List<String> techStack;
    private ProjectStatus status;
    private LocalDate startAt;
    private LocalDate endAt;
    private String github;
    private String prod;
    private String imageUrl;

    public ProjectModel(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.summary = project.getSummary();
        this.description = project.getDescription();
        this.techStack = project.getTechStack();
        this.status = project.getStatus();
        this.startAt = project.getStartAt();
        this.endAt = project.getEndAt();
        this.github = project.getGithub();
        this.prod = project.getProd();
        this.imageUrl = project.getImageUrl();
    }
}
