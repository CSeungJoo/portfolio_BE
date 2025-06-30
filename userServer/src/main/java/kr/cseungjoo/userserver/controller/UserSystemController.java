package kr.cseungjoo.userserver.controller;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.userserver.domain.User;
import kr.cseungjoo.userserver.dto.LoginDto;
import kr.cseungjoo.userserver.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/system")
@RequiredArgsConstructor
public class UserSystemController {

    private final UserFacade userFacade;

    @PostMapping("/login")
    public ResponseEntity<BasicResponse.BaseResponse> login(@RequestBody LoginDto loginDto) {
        User user = userFacade.login(loginDto);

        return BasicResponse.ok(user);
    }
}
