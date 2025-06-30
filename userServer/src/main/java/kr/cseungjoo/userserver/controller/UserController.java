package kr.cseungjoo.userserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.basic.util.BasicUtil;
import kr.cseungjoo.userserver.domain.User;
import kr.cseungjoo.userserver.dto.RegisterDto;
import kr.cseungjoo.userserver.dto.ReturnUserDto;
import kr.cseungjoo.userserver.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<BasicResponse.BaseResponse> register(@Validated @RequestBody RegisterDto registerDto) {
        User user = userFacade.register(registerDto);

        ReturnUserDto returnUserDto = new ReturnUserDto(user);

        return BasicResponse.ok(returnUserDto);
    }

    @GetMapping("/user/info")
    public ResponseEntity<BasicResponse.BaseResponse> info() {
        String email = BasicUtil.getPrincipal().getEmail();

        User user = userFacade.info(email);

        ReturnUserDto returnUserDto = new ReturnUserDto(user);

        return BasicResponse.ok(returnUserDto);
    }
}
