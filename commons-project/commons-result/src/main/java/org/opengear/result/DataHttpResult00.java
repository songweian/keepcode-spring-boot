package org.opengear.result;

import lombok.Getter;

@Getter
public class DataHttpResult00<T> extends BaseHttpResult00 {

    private final T data;

    public DataHttpResult00(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

}
