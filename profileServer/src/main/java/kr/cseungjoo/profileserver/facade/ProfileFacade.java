package kr.cseungjoo.profileserver.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.cseungjoo.commonmodule.Role;
import kr.cseungjoo.commonmodule.basic.dto.UserIdDto;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.security.jwt.provider.JwtProvider;
import kr.cseungjoo.profileserver.domain.Profile;
import kr.cseungjoo.profileserver.dto.CreateProfileDto;
import kr.cseungjoo.profileserver.dto.EditProfileDto;
import kr.cseungjoo.profileserver.exception.ProfileExistsException;
import kr.cseungjoo.profileserver.exception.ProfileNotFoundException;
import kr.cseungjoo.profileserver.feign.UserFeignClient;
import kr.cseungjoo.profileserver.model.ProfileModel;
import kr.cseungjoo.profileserver.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ProfileFacade {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final ProfileService profileService;
    private final UserFeignClient userFeignClient;

    public ProfileModel createProfile(CreateProfileDto createProfileDto, String email) {

        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);

        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        boolean exists = profileService.existsByUserId(userIdDto.getUserId());

        if (exists) {
            throw new ProfileExistsException();
        }

        Profile profile = profileService.create(
                createProfileDto.getImgUrl(),
                createProfileDto.getName(),
                createProfileDto.getEngName(),
                createProfileDto.getRole(),
                createProfileDto.getEmail(),
                createProfileDto.getPhoneNumber(),
                createProfileDto.getGithub(),
                createProfileDto.getBlog(),
                userIdDto.getUserId()
        );

        ProfileModel profileModel = new ProfileModel(profile);

        return profileModel;
    }

    public ProfileModel getInfo(String nickname) {

        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.nicknameToUserId(systemToken, nickname);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Profile profile = profileService.findByUserId(userIdDto.getUserId()).orElseThrow(
                ProfileNotFoundException::new
        );

        ProfileModel profileModel = new ProfileModel(profile);

        return profileModel;
    }

    public ProfileModel editProfile(String email, EditProfileDto editProfileDto) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        Profile profile = profileService.findByUserId(userIdDto.getUserId()).orElseThrow(
                ProfileNotFoundException::new
        );

        profile.modified(
                editProfileDto.getImgUrl(),
                editProfileDto.getName(),
                editProfileDto.getEngName(),
                editProfileDto.getRole(),
                editProfileDto.getEmail(),
                editProfileDto.getPhoneNumber(),
                editProfileDto.getGithub(),
                editProfileDto.getBlog()
        );

        Profile save = profileService.save(profile);

        ProfileModel profileModel = new ProfileModel(save);

        return profileModel;
    }

    public void removeProfile(String email) {
        String systemToken = "Bearer "+ jwtProvider.generateAccessToken("system@system.system", Collections.singletonMap("role", Role.SYSTEM));

        ResponseEntity<BasicResponse.BaseResponse> response = userFeignClient.emailToUserId(systemToken, email);
        UserIdDto userIdDto = objectMapper.convertValue(response.getBody().data(), UserIdDto.class);

        profileService.removeByUserId(userIdDto.getUserId());
    }
}
