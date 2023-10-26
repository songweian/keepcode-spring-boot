package org.opengear.commons.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Supplier;

@FunctionalInterface
public interface SneakSupplier<T> extends Supplier<T> {

    Logger innerLog = LoggerFactory.getLogger(SneakSupplier.class);

    T doSupplier() throws IOException;

    @Override
    default T get() {
        try {
            return doSupplier();
        } catch (Exception e) {
            innerLog.error("", e);
            return null;
        }
    }
}
