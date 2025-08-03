package kr.cseungjoo.commonmodule.exception.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.exception.CustomFeignException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;
import kr.cseungjoo.commonmodule.exception.GlobalException;

import java.nio.charset.StandardCharsets;

public class FeignExceptionDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Exception decode(String s, Response response) {
        try {
            String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            BasicResponse.BaseErrorResponse basicResponse = objectMapper.readValue(body, BasicResponse.BaseErrorResponse.class);
            String errorCodeStr = basicResponse.code();

            ErrorCode errorCode = ErrorCode.find(errorCodeStr);

            return new GlobalException(errorCode);
        }   catch (Exception e) {
            return new CustomFeignException();
        }
    }
}
