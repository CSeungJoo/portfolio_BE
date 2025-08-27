package kr.cseungjoo.profileserver.controller;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.basic.util.BasicUtil;
import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import kr.cseungjoo.profileserver.dto.CreateProfileDto;
import kr.cseungjoo.profileserver.dto.EditProfileDto;
import kr.cseungjoo.profileserver.dto.ReturnProfileDto;
import kr.cseungjoo.profileserver.facade.ProfileFacade;
import kr.cseungjoo.profileserver.model.ProfileModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileFacade profileFacade;

    @PostMapping("/create")
    public ResponseEntity<BasicResponse.BaseResponse> create(@RequestBody CreateProfileDto createProfileDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();
        ProfileModel profileModel = profileFacade.createProfile(createProfileDto, principalDetails.getEmail());

        ReturnProfileDto returnProfileDto = new ReturnProfileDto(profileModel);

        return BasicResponse.ok(returnProfileDto);
    }

    @GetMapping("/{nickname}/info")
    public ResponseEntity<BasicResponse.BaseResponse> info(@PathVariable("nickname") String nickname) {
        ProfileModel profileModel = profileFacade.getInfo(nickname);

        ReturnProfileDto returnProfileDto = new ReturnProfileDto(profileModel);

        return BasicResponse.ok(returnProfileDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<BasicResponse.BaseResponse> edit(@RequestBody EditProfileDto editProfileDto) {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        ProfileModel profileModel = profileFacade.editProfile(principalDetails.getEmail(), editProfileDto);

        ReturnProfileDto returnProfileDto = new ReturnProfileDto(profileModel);

        return BasicResponse.ok(returnProfileDto);
    }

    @PostMapping("/remove")
    public ResponseEntity<BasicResponse.BaseResponse> remove() {
        PrincipalDetails principalDetails = BasicUtil.getPrincipal();

        profileFacade.removeProfile(principalDetails.getEmail());

        return BasicResponse.ok("ok");
    }
}
