package kr.cseungjoo.commonmodule.basic.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

@UtilityClass
public class ApiUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            ip = ip.split(",")[0]; // 다중 프록시 환경
        }
        return ip;
    }

    public static String getHeadersAsString(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(name -> name + ": " + request.getHeader(name))
                .collect(Collectors.joining("\n"));
    }

    public static String getHeadersAsString(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(name -> name + ": " + response.getHeader(name))
                .collect(Collectors.joining("\n"));
    }

    public static String parseClientOs(String userAgent) {
        if (userAgent == null) return null;
        if (userAgent.contains("Windows")) return "Windows";
        if (userAgent.contains("Mac")) return "MacOS";
        if (userAgent.contains("X11")) return "Unix";
        if (userAgent.contains("Android")) return "Android";
        if (userAgent.contains("iPhone")) return "iOS";
        return "Unknown";
    }

    public static String parseClientBrowser(String userAgent) {
        if (userAgent == null) return null;
        if (userAgent.contains("Chrome")) return "Chrome";
        if (userAgent.contains("Firefox")) return "Firefox";
        if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) return "Safari";
        if (userAgent.contains("Edge")) return "Edge";
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) return "Internet Explorer";
        return "Unknown";
    }

    public static String parseClientDevice(String userAgent) {
        if (userAgent == null) return null;
        if (userAgent.contains("Mobile")) return "Mobile";
        if (userAgent.contains("Tablet")) return "Tablet";
        return "Desktop";
    }

    public static String parseClientDeviceType(String userAgent) {
        String device = parseClientDevice(userAgent);
        return switch (device) {
            case "Mobile" -> "Phone";
            case "Tablet" -> "Tablet";
            case "Desktop" -> "PC";
            default -> "Unknown";
        };
    }

    public static String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] buf = request.getContentAsByteArray();
        return new String(buf, StandardCharsets.UTF_8);
    }

    public static String getResponseBody(ContentCachingResponseWrapper response) {
        byte[] buf = response.getContentAsByteArray();
        return new String(buf, StandardCharsets.UTF_8);
    }
}
