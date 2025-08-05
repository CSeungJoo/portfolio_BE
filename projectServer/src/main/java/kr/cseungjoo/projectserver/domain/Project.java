package kr.cseungjoo.projectserver.domain;

import jakarta.persistence.*;
import kr.cseungjoo.commonmodule.converter.ListToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String summary;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description; // markdown 형식 그대로 저장

    @Column
    @Convert(converter = ListToStringConverter.class)
    private List<String> techStack;

    @Column
    private LocalDate startAt;

    @Column
    private LocalDate endAt;

    @Column
    private long userId;

    public void modify(String title, String summary, String description, List<String> techStack, LocalDate startAt, LocalDate endAt) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.techStack = techStack;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
