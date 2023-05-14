package roadshow.demo.api;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest; // or:  jakarta.servlet.http.HttpServletRequest

public final class HttpUtils {

    private static final String[] IP_HEADER_NAMES = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_X_FORWARDED_FOR",
        "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP",
        "HTTP_CLIENT_IP",
        "HTTP_FORWARDED_FOR",
        "HTTP_FORWARDED",
        "HTTP_VIA",
        "REMOTE_ADDR"

        // you can add more matching headers here ...
    };

    private HttpUtils() {
        // nothing here ...
    }

    public static String getRemoteIP(RequestAttributes requestAttributes)
    {
        if (requestAttributes == null)
        {
            return "0.0.0.0";
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        for (String header: IP_HEADER_NAMES) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();
    }
}