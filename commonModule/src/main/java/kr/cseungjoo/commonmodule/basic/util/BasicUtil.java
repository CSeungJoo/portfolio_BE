package kr.cseungjoo.commonmodule.basic.util;

import kr.cseungjoo.commonmodule.security.auth.PrincipalDetails;
import org.springframework.security.core.context.SecurityContextHolder;


public class BasicUtil {

    public static String getFullStackTrace(Throwable t) {
        StringBuilder sb = new StringBuilder();
        while (t != null) {
            sb.append(t.toString()).append("\n");
            for (StackTraceElement element : t.getStackTrace()) {
                sb.append("\tat ").append(element).append("\n");
            }
            t = t.getCause();
            if (t != null) sb.append("Caused by: ");
        }
        return sb.toString();
    }

    public static PrincipalDetails getPrincipal() {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principalDetails;
    }
}
