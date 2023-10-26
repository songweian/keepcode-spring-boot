package work.keepcode.commons.servlet;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class HttpServletResponseUtils {
    private HttpServletResponseUtils() {
    }

    public static void writeResponse(HttpServletResponse response, String contentType, byte[] content) throws IOException {
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }

    public static void writeResponse(HttpServletResponse response, String contentType, String content) throws IOException {
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(content.length());
        response.getWriter().write(content);
    }

    public static void setResponseHeader(HttpServletResponse response, String name, String value) {
        response.setHeader(name, value);
    }

    public static void addResponseHeader(HttpServletResponse response, String name, String value) {
        response.addHeader(name, value);
    }

    public static void setResponseContentType(HttpServletResponse response, String contentType) {
        response.setContentType(contentType);
    }

    public static void setResponseCharacterEncoding(HttpServletResponse response, String characterEncoding) {
        response.setCharacterEncoding(characterEncoding);
    }

    public static void setResponseContentLength(HttpServletResponse response, int contentLength) {
        response.setContentLength(contentLength);
    }

    public static void setResponseContentLength(HttpServletResponse response, long contentLength) {
        response.setContentLengthLong(contentLength);
    }

    public static void setResponseStatusCode(HttpServletResponse response, int statusCode) {
        response.setStatus(statusCode);
    }

}
