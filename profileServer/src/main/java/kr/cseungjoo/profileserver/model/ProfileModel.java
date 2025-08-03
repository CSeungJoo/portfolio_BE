package kr.cseungjoo.profileserver.model;

import kr.cseungjoo.profileserver.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {

    private long profileId;

    private String imgUrl;

    private String name;

    private String engName;

    private String role;

    private String email;

    private String phoneNumber;

    private String github;

    private String blog;

    public ProfileModel(Profile profile) {
        this.profileId = profile.getId();
        this.imgUrl = profile.getImgUrl();
        this.name = profile.getName();
        this.engName = profile.getEngName();
        this.role = profile.getRole();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.github = profile.getGithub();
        this.blog = profile.getBlog();
    }
}
