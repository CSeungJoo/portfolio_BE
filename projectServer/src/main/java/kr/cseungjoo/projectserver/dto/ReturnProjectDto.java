package kr.cseungjoo.projectserver.dto;

import kr.cseungjoo.projectserver.domain.ProjectStatus;
import kr.cseungjoo.projectserver.model.ProjectModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProjectDto {
    private long projectId;
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

    public ReturnProjectDto(ProjectModel projectModel) {
        this.projectId = projectModel.getId();
        this.title = projectModel.getTitle();
        this.summary = projectModel.getSummary();
        this.description = projectModel.getDescription();
        this.techStack = projectModel.getTechStack();
        this.status = projectModel.getStatus();
        this.startAt = projectModel.getStartAt();
        this.endAt = projectModel.getEndAt();
        this.github = projectModel.getGithub();
        this.imageUrl = projectModel.getImageUrl();
    }
}
