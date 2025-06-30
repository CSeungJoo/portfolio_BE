package kr.cseungjoo.authserver.feign;

import kr.cseungjoo.authserver.dto.LoginDto;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", path = "/user/system")
public interface AuthFeignClient {

    @PostMapping("/login")
    ResponseEntity<BasicResponse.BaseResponse> login(
            @RequestHeader("Authorization") String jwt,
            @RequestBody LoginDto loginDto
    );
}
