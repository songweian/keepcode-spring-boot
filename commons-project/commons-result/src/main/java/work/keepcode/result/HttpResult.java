package work.keepcode.result;

public interface HttpResult extends Result0 {

    boolean isSuccess();

    default boolean isError() {
        return !isSuccess();
    }

    default String getErrorMessage() {
        return "";
    }
}
