package kr.cseungjoo.commonmodule.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.cseungjoo.commonmodule.basic.response.BasicResponse;
import kr.cseungjoo.commonmodule.exception.BasicException;
import kr.cseungjoo.commonmodule.exception.ErrorCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityExceptionHandler extends OncePerRequestFilter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String responseValue = null;

        try {
            filterChain.doFilter(request, response);
        }catch (BasicException e) {
            response.setStatus(e.getErrorCode().getStatus().value());

            responseValue = objectMapper.writeValueAsString(
                    BasicResponse.error(e.getErrorCode())
            );
        }catch (JwtException e) {
            response.setStatus(ErrorCode.AUTH_FAILED.getStatus().value());

            responseValue = objectMapper.writeValueAsString(
                    BasicResponse.error(ErrorCode.AUTH_FAILED)
            );
        }finally {
            if (responseValue != null && !response.isCommitted()) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(responseValue);
            }
        }
    }
}