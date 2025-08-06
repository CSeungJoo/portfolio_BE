package kr.cseungjoo.projectserver.model;

import kr.cseungjoo.projectserver.domain.Project;
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
    private LocalDate startAt;
    private LocalDate endAt;

    public ProjectModel(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.summary = project.getSummary();
        this.description = project.getDescription();
        this.techStack = project.getTechStack();
        this.startAt = project.getStartAt();
        this.endAt = project.getEndAt();
    }
}
