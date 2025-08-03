package kr.cseungjoo.profileserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileDto {
    private String imgUrl;

    private String name;

    private String engName;

    private String role;

    private String email;

    private String phoneNumber;

    private String github;

    private String blog;
}
