package kr.cseungjoo.awardserver.feign;

import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", path = "/user/system")
public interface UserFeignClient {

    @GetMapping("/id/by-email")
    ResponseEntity<BasicResponse.BaseResponse> emailToUserId(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String email
    );

    @GetMapping("/id/by-nickname")
    ResponseEntity<BasicResponse.BaseResponse> nicknameToUserId(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String nickname);
}