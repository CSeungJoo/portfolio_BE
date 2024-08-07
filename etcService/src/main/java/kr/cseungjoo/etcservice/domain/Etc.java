package kr.cseungjoo.etcservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Etc {
    @Id @GeneratedValue
    private Long id;

    @Column
    private String day;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private EtcType type;

    @Column
    private String relatedOrganization;

    @Column
    private String introduce;

    @Column
    private Long usersId;
}
