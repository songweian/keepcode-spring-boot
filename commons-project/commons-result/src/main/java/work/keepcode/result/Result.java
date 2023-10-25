package work.keepcode.result;

public sealed interface Result<T> extends Result0 {
    static <T> Some<T> some(T value) {
        return new Some<>(value);
    }

    static <T> None<T> none() {
        //noinspection unchecked
        return (None<T>) None.INSTANCE;
    }

    @Override
    default boolean isError() {
        return this.getClass().isAssignableFrom(Error.class);
    }

    @Override
    default boolean isSuccess() {
        return !isError();
    }

    default boolean hasSome() {
        return this.getClass().isAssignableFrom(Some.class);
    }

    final class Some<T> implements Result {
        public final T value;

        public Some(T value) {
            this.value = value;
        }

        public T val() {
            return value;
        }
    }

    final class None<T> implements Result {

        private static final None<?> INSTANCE = new None<>();

        public None() {
        }
    }

    final class Error<T> implements Result {

        private static final Error<?> INSTANCE = new Error<>();

        public Error() {
        }
    }
}

