package kr.cseungjoo.projectserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDto {
    private String title;
    private String summary;
    private String description;
    private List<String> techStack;
    private LocalDate startAt;
    private LocalDate endAt;
}
