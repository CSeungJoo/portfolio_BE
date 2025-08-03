package kr.cseungjoo.profileserver.dto;

import kr.cseungjoo.profileserver.domain.Profile;
import kr.cseungjoo.profileserver.model.ProfileModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProfileDto {
    private String imgUrl;

    private String name;

    private String engName;

    private String role;

    private String email;

    private String phoneNumber;

    private String github;

    private String blog;

    public ReturnProfileDto(ProfileModel profileModel) {
        this.imgUrl = profileModel.getImgUrl();
        this.name = profileModel.getName();
        this.engName = profileModel.getEngName();
        this.role = profileModel.getRole();
        this.email = profileModel.getEmail();
        this.phoneNumber = profileModel.getPhoneNumber();
        this.github = profileModel.getGithub();
        this.blog = profileModel.getBlog();
    }

    public ReturnProfileDto(Profile profile) {
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
