package work.keepcode.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class MDCUtils {

    public static void clearQuietly() {
        MDC.clear();
    }
}
