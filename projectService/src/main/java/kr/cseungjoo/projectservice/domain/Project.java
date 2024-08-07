package kr.cseungjoo.projectservice.domain;

import jakarta.persistence.*;
import kr.cseungjoo.commonmodule.convert.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String introduce;

    @Column
    private String blogLink;

    @Column
    private String productionLink;

    @Column
    private String repositoryLink;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<Integer> techStack_id;

    @Column
    private int users_id;
}
