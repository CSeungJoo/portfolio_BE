package kr.cseungjoo.authserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.cseungjoo.authserver.dto.LoginDto;
import kr.cseungjoo.authserver.dto.TokenDto;
import kr.cseungjoo.authserver.exception.RefreshTokenNotFoundException;
import kr.cseungjoo.authserver.service.AuthService;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BasicResponse.BaseResponse> login(@Validated @RequestBody LoginDto loginDto) {
        TokenDto tokenDto = authService.login(loginDto);

        return BasicResponse.ok(tokenDto);
    }

    @GetMapping("/refresh")
    public ResponseEntity<BasicResponse.BaseResponse> refresh(HttpServletRequest req) {
        String header = req.getHeader("Refresh-Token");

        if (header == null) {
            throw new RefreshTokenNotFoundException(ErrorCode.STR_REFRESH_TOKEN_NOT_FOUND);
        }

        TokenDto tokenDto = authService.refreshToken(header);

        return BasicResponse.ok(tokenDto);
    }
}
