package work.keepcode.commons.lang;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Supplier;

import static work.keepcode.commons.lang.HttpHeaders.X_FORWARDED_FOR;

@Slf4j
public class HttpServletRequestUtils {
    private HttpServletRequestUtils() {
    }

    public static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static Charset getCharset(HttpServletRequest request) {
        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            return DEFAULT_CHARSET;
        }
        return Charset.forName(characterEncoding);
    }

    public static byte[] readBody(HttpServletRequest request) {
        return IOUtils.readAsBytesQuietly(new SneakSupplier<InputStream>() {
            @Override
            public InputStream doSupplier() throws IOException {
                return request.getInputStream();
            }
        });
    }

    public static String getRequestId(HttpServletRequest request, Supplier<String> requestIdSupplier) {
        String header = request.getHeader("X-Request-Id");
        if (StringUtils.isBlank(header)) {
            return requestIdSupplier.get();
        }
        return header;
    }

    /**
     * get the real client ip
     */
    public static String getClientIP(HttpServletRequest request) {
        String ips = request.getHeader(X_FORWARDED_FOR);
        if (StringUtils.isBlank(ips)) {
            return "";
        }
        int index = ips.indexOf(",");
        if (index != -1) {
            return ips.substring(0, index);
        }
        return ips;
    }

    /**
     * get remote server ip
     */
    public static String getRemoteServerIP(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        return ip;
    }

    public static HttpServletRequest wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return request;
        }
        return new ContentCachingRequestWrapper(request);
    }

    public static HttpServletResponse wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return response;
        }
        return new ContentCachingResponseWrapper(response);
    }

    public static String headersAsString(HttpServletRequest request) {
        List<String> requestHeaders = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeaders.add(headerName + "=" + headerValue);
        }
        String requestHeaderString = StringUtils.join(requestHeaders, ";");
        return requestHeaderString;
    }
}
