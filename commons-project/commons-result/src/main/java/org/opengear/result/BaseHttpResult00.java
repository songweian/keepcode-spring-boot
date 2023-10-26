package org.opengear.result;

import lombok.Getter;

@Getter
public class BaseHttpResult00 implements HttpResult {

    protected final String code;
    protected final String message;

    public BaseHttpResult00(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> DataHttpResult00<T> ok(T data) {
        return new DataHttpResult00<>("0", "OK", data);
    }

    public static BaseHttpResult00 ok() {
        return new BaseHttpResult00("200", "OK");
    }

    public static BaseHttpResult00 error500() {
        return new BaseHttpResult00("500", "Internal Server Error");
    }

    public static BaseHttpResult00 error400() {
        return new BaseHttpResult00("400", "client request invalid");
    }

    public static BaseHttpResult00 error404() {
        return new BaseHttpResult00("404", "RESOURCE NOT FOUND");
    }

    public static BaseHttpResult00 error(String code, String message) {
        return new BaseHttpResult00(code, message);
    }

    @Override
    public boolean isSuccess() {
        return "0".equals(code);
    }

}
