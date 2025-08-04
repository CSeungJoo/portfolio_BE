package kr.cseungjoo.profileserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imgUrl;

    @Column
    private String name;

    @Column
    private String engName;

    @Column
    private String role;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String github;

    @Column
    private String blog;

    @Column
    private Long userId;

    public void modified(String imgUrl, String name, String engName, String role, String email, String phoneNumber, String github, String blog) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.engName = engName;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.github = github;
        this.blog = blog;
    }
}
