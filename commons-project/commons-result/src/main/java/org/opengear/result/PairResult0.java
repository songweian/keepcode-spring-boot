package org.opengear.result;

public interface PairResult0<R, E> extends Result0 {

    R data();

    E error();

    default boolean isError() {
        return error() != null;
    }

    default boolean isSuccess() {
        return !isError();
    }

}
