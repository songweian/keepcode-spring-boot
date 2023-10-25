package work.keepcode.commons.lang;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public final class IOUtils {
    private IOUtils() {
    }

    public static byte[] readAsBytes(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[0];
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int len;
        while ((len = inputStream.read(buffer)) > -1) {
            out.write(buffer, 0, len);
        }
        return out.toByteArray();
    }

    public static byte[] readAsBytesQuietly(InputStream inputStream) {
        try {
            return IOUtils.readAsBytes(inputStream);
        } catch (Exception e) {
            log.error("read inputStream error", e);
            return new byte[0];
        }
    }

    public static byte[] readAsBytesQuietly(SneakSupplier<InputStream> supplier) {
        try {
            return IOUtils.readAsBytes(supplier.get());
        } catch (Exception e) {
            log.error("read inputStream error", e);
            return new byte[0];
        }
    }
}
