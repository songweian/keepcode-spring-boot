package work.keepcode.commons.servlet;

import com.google.common.collect.Lists;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;

public final class HttpServletRequestUtils {

    public static final int BUFFER_SIZE = 1024;

    private HttpServletRequestUtils() {
    }

    public static Charset getResponseEncoding(HttpServletRequest request, Charset defaultCharset) {
        String characterEncoding = request.getCharacterEncoding();
        if (characterEncoding == null) {
            return defaultCharset;
        }
        return Charset.forName(characterEncoding);
    }

    public static String getRequestEncoding(HttpServletRequest request) {
        String characterEncoding = request.getCharacterEncoding();
        if (StringUtils.isBlank(characterEncoding)) {
            return null;
        }
        return characterEncoding;
    }

    public static String getContentType(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (StringUtils.isBlank(contentType)) {
            return null;
        }
        return contentType;
    }

    public static ArrayListValuedHashMap<String, String> getHeaderMap(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        ArrayListValuedHashMap<String, String> headers = new ArrayListValuedHashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String[] headerValues = request.getParameterValues(headerName);
            headers.putAll(headerName, Lists.newArrayList(headerValues));
        }
        return headers;
    }


    public static byte[] readBodyAsBytes(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            if (inputStream == null) {
                return null;
            }
            return readBodyAsBytes(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] readBodyAsBytes(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            byte[] buffer = new byte[BUFFER_SIZE];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, len);
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String readBodyAsString(HttpServletRequest request) {
        return readBodyAsString(request, getResponseEncoding(request, Charset.defaultCharset()));
    }

    public static String readBodyAsString(HttpServletRequest request, Charset responseEncoding) {
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            return readBodyAsString(inputStream, responseEncoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readBodyAsString(ServletInputStream inputStream, Charset responseEncoding) {
        byte[] bytes = readBodyAsBytes(inputStream);
        return new String(bytes, responseEncoding);
    }

}
