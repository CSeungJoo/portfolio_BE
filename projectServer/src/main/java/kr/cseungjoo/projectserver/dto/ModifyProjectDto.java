package kr.cseungjoo.projectserver.dto;

import kr.cseungjoo.projectserver.domain.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProjectDto {
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
}
