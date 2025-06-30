package kr.cseungjoo.authserver.controller;

import kr.cseungjoo.authserver.dto.LoginDto;
import kr.cseungjoo.authserver.service.AuthService;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BasicResponse.BaseResponse> login(@Validated @RequestBody LoginDto loginDto) {
        ResponseEntity<BasicResponse.BaseResponse> login = authService.login(loginDto);

        return login;
    }
}
