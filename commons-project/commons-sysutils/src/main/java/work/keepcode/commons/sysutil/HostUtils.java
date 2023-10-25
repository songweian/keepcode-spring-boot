package work.keepcode.commons.sysutil;

public class HostUtils {

    public static String getHostName() {
        return System.getenv("COMPUTERNAME");
    }

}
