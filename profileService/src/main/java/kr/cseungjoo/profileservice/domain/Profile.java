package kr.cseungjoo.profileservice.domain;

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
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imgUrl;

    @Column
    private String introduce;

    @Column
    private Long users_id;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<Integer> jobCategory_id;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<Integer> techStack_id;

    public Profile(String imgUrl, String introduce, Long users_id, List<Integer> jobCategory_id, List<Integer> techStack_id) {
        this.imgUrl = imgUrl;
        this.introduce = introduce;
        this.users_id = users_id;
        this.jobCategory_id = jobCategory_id;
        this.techStack_id = techStack_id;
    }
}
