package work.keepcode.configdal.support;

public class ConfigdalExceptionUtils {


    public static void wrapException(String message) {
        throw new ConfigdalException(message);
    }

    public static void wrapException(String message, Throwable cause) {
        throw new ConfigdalException(message, cause);
    }

    public static ConfigdalException wrapException(Throwable cause) {
        return new ConfigdalException(cause);
    }

    public static void wrapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        throw new ConfigdalException(message, cause, enableSuppression, writableStackTrace);
    }

    public static class ConfigdalException extends RuntimeException {

        public ConfigdalException() {
        }

        public ConfigdalException(String message) {
            super(message);
        }

        public ConfigdalException(String message, Throwable cause) {
            super(message, cause);
        }

        public ConfigdalException(Throwable cause) {
            super(cause);
        }

        public ConfigdalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

    }
}
